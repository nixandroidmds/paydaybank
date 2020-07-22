package com.payday.bank.view.activity

import android.content.Intent
import android.os.Bundle
import com.payday.bank.R
import com.payday.bank.presentation.transactions.TransactionsViewModel
import com.payday.bank.view.activity.base.BaseActivity
import com.payday.bank.view.fragment.transactions.TransactionsFilteredFragment
import com.payday.bank.view.navigation.ActivityScreen

class MainActivity : BaseActivity<TransactionsViewModel>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            router.newRootScreen(TransactionsFilteredFragment.newInstance())
        }
    }

    companion object {

        fun newInstance() = ActivityScreen { Intent(it, MainActivity::class.java) }
    }
}