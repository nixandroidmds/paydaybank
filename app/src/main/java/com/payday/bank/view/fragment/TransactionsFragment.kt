package com.payday.bank.view.fragment

import android.os.Bundle
import android.view.View
import com.payday.bank.R
import com.payday.bank.presentation.EmptyViewModel
import com.payday.bank.view.navigation.FragmentScreen

class TransactionsFragment : BaseFragment<EmptyViewModel>(R.layout.fragment_transactions) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance() = FragmentScreen { TransactionsFragment() }
    }
}