package com.payday.bank.di.module.view

import com.payday.bank.di.module.view.authentication.AuthenticationActivityModule
import com.payday.bank.di.module.view.authentication.AuthenticationFragmentContributorModule
import com.payday.bank.di.module.view.authentication.AuthenticationViewModelModule
import com.payday.bank.di.module.view.main.MainActivityModule
import com.payday.bank.di.module.view.main.MainFragmentContributorModule
import com.payday.bank.di.module.view.main.MainViewModelModule
import com.payday.bank.di.module.view.splash.SplashActivityModule
import com.payday.bank.di.module.view.splash.SplashViewModelModule
import com.payday.bank.di.scope.ActivityScope
import com.payday.bank.view.activity.AuthenticationActivity
import com.payday.bank.view.activity.MainActivity
import com.payday.bank.view.activity.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module abstract class ActivitiesContributorModule {

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            RouterModule::class,
            SplashViewModelModule::class,
            SplashActivityModule::class
        ]
    )
    abstract fun contributesSplashActivityInjector(): SplashActivity

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            RouterModule::class,
            AuthenticationFragmentContributorModule::class,
            AuthenticationViewModelModule::class,
            AuthenticationActivityModule::class
        ]
    )
    abstract fun contributesAuthenticationActivityInjector(): AuthenticationActivity

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            RouterModule::class,
            MainViewModelModule::class,
            MainFragmentContributorModule::class,
            MainActivityModule::class
        ]
    )
    abstract fun contributesMainActivityInjector(): MainActivity
}