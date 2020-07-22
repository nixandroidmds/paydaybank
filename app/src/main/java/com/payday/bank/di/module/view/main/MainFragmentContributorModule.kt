package com.payday.bank.di.module.view.main

import com.payday.bank.di.scope.FragmentScope
import com.payday.bank.view.fragment.transactions.TransactionsFilterFragment
import com.payday.bank.view.fragment.transactions.TransactionsFilteredFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module interface MainFragmentContributorModule {

    @ContributesAndroidInjector @FragmentScope
    fun contributesTransactionsFilteredFragmentInjector(): TransactionsFilteredFragment

    @ContributesAndroidInjector @FragmentScope
    fun contributesTransactionsFilterFragmentInjector(): TransactionsFilterFragment
}