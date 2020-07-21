package com.payday.bank.di.module.view.authentication

import androidx.lifecycle.ViewModel
import com.payday.bank.di.mapkey.ViewModelKey
import com.payday.bank.presentation.authentication.AuthenticationViewModel
import com.payday.bank.presentation.base.EmptyViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module interface AuthenticationViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(EmptyViewModel::class)
    fun bindEmptyViewModel(viewModel: EmptyViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AuthenticationViewModel::class)
    fun bindAuthenticationViewModel(viewModel: AuthenticationViewModel): ViewModel
}