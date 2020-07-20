package com.payday.bank.di.module.view.splash

import androidx.lifecycle.ViewModel
import com.payday.bank.di.mapkey.ViewModelKey
import com.payday.bank.di.module.data.repository.RepositoryModule
import com.payday.bank.presentation.viewmodel.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [RepositoryModule::class])
interface SplashViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel
}