package com.payday.bank.di.module.view.main

import androidx.lifecycle.ViewModel
import com.payday.bank.di.mapkey.ViewModelKey
import com.payday.bank.presentation.base.EmptyViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module interface MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(EmptyViewModel::class)
    fun bindEmptyViewModel(viewModel: EmptyViewModel): ViewModel
}