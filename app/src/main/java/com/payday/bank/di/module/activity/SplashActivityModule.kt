package com.payday.bank.di.module.activity

import com.payday.bank.di.scope.ActivityScope
import com.payday.bank.view.activity.BaseActivity
import com.payday.bank.view.activity.SplashActivity
import com.payday.bank.view.navigation.BaseNavigator
import com.payday.bank.view.navigation.DefaultNavigator
import dagger.Binds
import dagger.Module

@Module interface SplashActivityModule {

    @Binds @ActivityScope
    fun bindActivity(activity: SplashActivity): BaseActivity<*>

    @Binds @ActivityScope
    fun bindNavigator(navigator: DefaultNavigator): BaseNavigator
}