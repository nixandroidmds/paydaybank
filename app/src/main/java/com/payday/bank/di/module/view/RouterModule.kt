package com.payday.bank.di.module.view

import com.payday.bank.di.scope.ActivityScope
import com.payday.bank.view.navigation.navigator.BaseNavigator
import com.payday.bank.view.navigation.navigator.DefaultNavigator
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

@Module class RouterModule {

    @Provides @ActivityScope
    fun provideCicerone(): Cicerone<Router> = Cicerone.create(Router())

    @Provides @ActivityScope
    fun provideRouter(cicerone: Cicerone<Router>): Router = cicerone.router

    @Provides @ActivityScope
    fun provideNavigatorHolder(cicerone: Cicerone<Router>): NavigatorHolder =
        cicerone.navigatorHolder

    @Provides @ActivityScope
    fun provideDefaultNavigator(navigator: DefaultNavigator): BaseNavigator =
        navigator
}