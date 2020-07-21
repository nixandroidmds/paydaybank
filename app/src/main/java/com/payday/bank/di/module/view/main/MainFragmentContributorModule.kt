package com.payday.bank.di.module.view.main

import com.payday.bank.di.scope.FragmentScope
import com.payday.bank.view.fragment.TransactionsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module interface MainFragmentContributorModule {

    @ContributesAndroidInjector @FragmentScope
    abstract fun contributesTransactionsFragmentInjector(): TransactionsFragment
}