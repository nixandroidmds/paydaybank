package com.payday.bank.view.activity.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.google.android.material.snackbar.Snackbar
import com.payday.bank.R
import com.payday.bank.presentation.base.BaseViewModel
import com.payday.bank.presentation.base.ViewModelFactory
import com.payday.bank.util.extension.getGenericClass
import com.payday.bank.view.navigation.navigator.BaseNavigator
import dagger.android.support.DaggerAppCompatActivity
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

abstract class BaseActivity<VM : BaseViewModel>(@LayoutRes contentLayoutId: Int) :
    DaggerAppCompatActivity(contentLayoutId) {

    @Inject protected lateinit var viewModelFactory: ViewModelFactory

    @Inject protected lateinit var navigator: BaseNavigator

    @Inject protected lateinit var navigatorHolder: NavigatorHolder

    @Inject protected lateinit var router: Router

    protected val viewModel: VM by lazy {
        val provider = ViewModelProvider(this, viewModelFactory)
        val viewModelClass = javaClass.getGenericClass<VM>(0)
        provider.get(viewModelClass)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.errorLiveEvent.observe(this, ::onError)
    }

    fun onError(errorMessage: String) {
        val coordinatorLayout = findViewById<View?>(R.id.coordinatorLayout)

        if (coordinatorLayout == null) {
            Timber.w("View for snackbar not found")
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        } else {
            Snackbar.make(coordinatorLayout, errorMessage, Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}