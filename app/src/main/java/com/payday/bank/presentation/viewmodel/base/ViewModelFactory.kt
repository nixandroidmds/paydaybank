package com.payday.bank.presentation.viewmodel.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.payday.bank.di.scope.ActivityScope
import javax.inject.Inject
import javax.inject.Provider

@ActivityScope class ViewModelFactory @Inject constructor(
    private val viewModelMap: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        modelClass.cast(viewModelMap[modelClass]?.get()) ?: throw UnsupportedOperationException()
}