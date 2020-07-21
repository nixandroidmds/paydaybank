package com.payday.bank.di.module.view.authentication

import com.payday.bank.di.scope.ActivityScope
import com.payday.bank.view.activity.authentication.AuthenticationActivity
import com.payday.bank.view.activity.base.BaseActivity
import dagger.Binds
import dagger.Module

@Module interface AuthenticationActivityModule {

    @Binds @ActivityScope
    fun bindActivity(activity: AuthenticationActivity): BaseActivity<*>
}