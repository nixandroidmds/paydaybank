package com.payday.bank.di.module.view.main

import androidx.lifecycle.ViewModel
import com.payday.bank.di.mapkey.ViewModelKey
import com.payday.bank.presentation.base.EmptyViewModel
import com.payday.bank.presentation.transactions.TransactionsFilteredViewModel
import com.payday.bank.presentation.transactions.TransactionsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module interface MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(EmptyViewModel::class)
    fun bindEmptyViewModel(viewModel: EmptyViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TransactionsViewModel::class)
    fun bindTransactionsViewModel(viewModel: TransactionsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TransactionsFilteredViewModel::class)
    fun bindTransactionsFilteredViewModel(viewModel: TransactionsFilteredViewModel): ViewModel
}