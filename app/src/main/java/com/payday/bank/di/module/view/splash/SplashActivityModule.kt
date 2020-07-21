package com.payday.bank.di.module.view.splash

import com.payday.bank.di.scope.ActivityScope
import com.payday.bank.view.activity.BaseActivity
import com.payday.bank.view.activity.SplashActivity
import dagger.Binds
import dagger.Module

@Module interface SplashActivityModule {

    @Binds @ActivityScope
    fun bindActivity(activity: SplashActivity): BaseActivity<*>
}