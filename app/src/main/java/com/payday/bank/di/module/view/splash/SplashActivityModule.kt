package com.payday.bank.di.module.view.splash

import com.payday.bank.di.scope.ActivityScope
import com.payday.bank.view.activity.authentication.SplashActivity
import com.payday.bank.view.activity.base.BaseActivity
import dagger.Binds
import dagger.Module

@Module interface SplashActivityModule {

    @Binds @ActivityScope
    fun bindActivity(activity: SplashActivity): BaseActivity<*>
}