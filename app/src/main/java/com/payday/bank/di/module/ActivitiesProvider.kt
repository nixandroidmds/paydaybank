package com.payday.bank.di.module

import com.payday.bank.di.module.activity.SplashActivityModule
import com.payday.bank.di.module.activity.SplashViewModelModule
import com.payday.bank.di.scope.ActivityScope
import com.payday.bank.view.activity.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module abstract class ActivitiesProvider {

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