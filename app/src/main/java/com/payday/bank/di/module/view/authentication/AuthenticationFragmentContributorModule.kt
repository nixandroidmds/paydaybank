package com.payday.bank.di.module.view.authentication

import com.payday.bank.di.scope.FragmentScope
import com.payday.bank.view.fragment.AuthenticationFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module interface AuthenticationFragmentContributorModule {

    @ContributesAndroidInjector @FragmentScope
    fun contributesAuthenticationFragmentInjector(): AuthenticationFragment
}