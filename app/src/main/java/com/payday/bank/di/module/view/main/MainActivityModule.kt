package com.payday.bank.di.module.view.main

import com.payday.bank.di.scope.ActivityScope
import com.payday.bank.view.activity.BaseActivity
import com.payday.bank.view.activity.MainActivity
import dagger.Binds
import dagger.Module

@Module interface MainActivityModule {

    @Binds @ActivityScope
    fun bindActivity(activity: MainActivity): BaseActivity<*>
}