package com.payday.bank.di.component

import android.content.Context
import com.payday.bank.Application
import com.payday.bank.di.module.ContextModule
import com.payday.bank.di.module.view.ActivitiesContributorModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivitiesContributorModule::class,
        ContextModule::class
    ]
)
interface AppComponent : AndroidInjector<Application> {

    @Component.Builder interface Builder {

        @BindsInstance fun context(context: Context): Builder

        fun build(): AppComponent
    }
}