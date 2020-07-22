package com.payday.bank.view.fragment.transactions

import android.os.Bundle
import android.view.View
import com.payday.bank.R
import com.payday.bank.domain.entity.AccountDomainEntity
import com.payday.bank.presentation.base.EmptyViewModel
import com.payday.bank.view.entity.TransactionFilterUiEntity
import com.payday.bank.view.fragment.base.BaseFragment
import com.payday.bank.view.navigation.FragmentScreen

class TransactionsFilterFragment :
    BaseFragment<EmptyViewModel>(R.layout.fragment_filter_transactions) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHomeIconEnabled(true)
    }

    override val menuRes get() = R.menu.empty

    companion object {

        fun newInstance(
            accountList: List<AccountDomainEntity>,
            transactionFilter: TransactionFilterUiEntity
        ) = FragmentScreen {
            TransactionsFilterFragment()
        }
    }
}