package com.payday.bank.presentation.viewmodel.base

import androidx.annotation.WorkerThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import com.payday.bank.BuildConfig
import com.payday.bank.domain.exception.MessageException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import timber.log.Timber
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseViewModel : ViewModel() {

    private val scope: CoroutineScope

    val errorLiveEvent = LiveEvent<String>()

    init {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            when {
                viewModelScope.isActive -> viewModelScope.launch { onFailure(throwable) }

                BuildConfig.DEBUG -> {
                    val thread = Thread.currentThread()
                    thread.uncaughtExceptionHandler?.uncaughtException(thread, throwable)
                }

                else -> Timber.e(throwable, "Error without subscriber")

            }
        }

        val scope = viewModelScope + exceptionHandler + Dispatchers.Default

        this.scope = if (BuildConfig.DEBUG) scope + CoroutineName(javaClass.simpleName) else scope
    }

    @WorkerThread open fun onCancellation(e: CancellationException) {
        Timber.i(e)
    }

    @WorkerThread open fun onFailure(t: Throwable) {
        if (t is MessageException) {
            errorLiveEvent.postValue(t.getMessageForUser())
        } else {
            Timber.e(t)
        }
    }

    protected fun launchSafe(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        onStart: (CoroutineScope.() -> Unit)? = null,
        onCancellation: ((e: CancellationException) -> Unit)? = null,
        onFailure: ((t: Throwable) -> Unit)? = null,
        block: CoroutineScope.() -> Unit
    ) = scope.launch(context, start) {
        try {
            onStart?.invoke(this)
            block()
        } catch (e: CancellationException) {
            (onCancellation ?: ::onCancellation).invoke(e)
        } catch (t: Throwable) {
            (onFailure ?: ::onFailure).invoke(t)
        }
    }
}