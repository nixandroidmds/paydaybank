package com.payday.bank.di.module.view

import com.payday.bank.di.scope.ActivityScope
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

@Module class RouterModule {

    @ActivityScope @Provides
    fun provideCicerone(): Cicerone<Router> = Cicerone.create(Router())

    @ActivityScope @Provides
    fun provideRouter(cicerone: Cicerone<Router>): Router = cicerone.router

    @ActivityScope @Provides
    fun provideNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder =
        cicerone.navigatorHolder
}