package com.payday.bank.di.module.view

import com.payday.bank.di.module.view.splash.SplashActivityModule
import com.payday.bank.di.module.view.splash.SplashViewModelModule
import com.payday.bank.di.scope.ActivityScope
import com.payday.bank.view.activity.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module interface ActivitiesContributorModule {

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            RouterModule::class,
            SplashViewModelModule::class,
            SplashActivityModule::class
        ]
    )
    abstract fun contributesSplashActivityInjector(): SplashActivity
}