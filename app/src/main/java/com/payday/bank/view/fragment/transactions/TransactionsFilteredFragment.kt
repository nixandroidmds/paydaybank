package com.payday.bank.view.fragment.transactions

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.observe
import com.payday.bank.R
import com.payday.bank.presentation.transactions.TransactionsFilteredViewModel
import com.payday.bank.presentation.transactions.TransactionsViewModel
import com.payday.bank.util.date.DateUtils
import com.payday.bank.view.activity.authentication.AuthenticationActivity
import com.payday.bank.view.adapter.recycler.transaction.TransactionsAdapter
import com.payday.bank.view.fragment.base.BaseFragment
import com.payday.bank.view.navigation.FragmentScreen
import kotlinx.android.synthetic.main.fragment_filtered_transactions.list
import kotlinx.android.synthetic.main.fragment_filtered_transactions.swipeRefreshLayout
import kotlinx.android.synthetic.main.fragment_filtered_transactions.transactionsNotFountTextView

class TransactionsFilteredFragment :
    BaseFragment<TransactionsFilteredViewModel>(R.layout.fragment_filtered_transactions) {

    private val adapter by lazy { TransactionsAdapter() }

    private val sharedViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get<TransactionsViewModel>()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity()

        setHomeIconEnabled(true)
        activity.findViewById<Toolbar>(R.id.toolbar).setNavigationIcon(R.drawable.ic_ui_exit)

        sharedViewModel.accountListLiveData.observe(viewLifecycleOwner) {
            requireActivity().invalidateOptionsMenu()
        }
        sharedViewModel.transactionUiListLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)

            if (it.isEmpty()) {
                transactionsNotFountTextView.isVisible = true
                transactionsNotFountTextView.text = getString(
                    R.string.transactions_not_fount_pattern,
                    DateUtils.formatToUserDate(sharedViewModel.transactionFilter.dateFrom),
                    DateUtils.formatToUserDate(sharedViewModel.transactionFilter.dateTo)
                )
            } else {
                transactionsNotFountTextView.isGone = true
            }
        }
        sharedViewModel.progressLiveData.observe(
            viewLifecycleOwner,
            swipeRefreshLayout::setRefreshing
        )
        viewModel.logOutLiveEvent.observe(viewLifecycleOwner) {
            router.replaceScreen(AuthenticationActivity.newInstance())
        }

        list.adapter = adapter

        swipeRefreshLayout.setOnRefreshListener(sharedViewModel::refresh)
    }

    override val menuRes: Int
        get() =
            if (sharedViewModel.accountListLiveData.value.isNullOrEmpty()) {
                R.menu.empty
            } else {
                R.menu.transactions_filtered
            }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                AlertDialog.Builder(requireContext())
                    .setTitle(R.string.transactions_logout_title)
                    .setMessage(R.string.transactions_logout_message)
                    .setPositiveButton(R.string.transactions_logout_positive) { _, _ ->
                        viewModel.logout()
                    }.setNeutralButton(R.string.transactions_logout_cancel, null)
                    .show()
            }

            R.id.filterMenu -> {
                router.navigateTo(
                    TransactionsFilterFragment.newInstance(
                        accountList = sharedViewModel.accountListLiveData.value ?: listOf(),
                        transactionFilter = sharedViewModel.transactionFilter
                    )
                )
            }

            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    override fun onDestroyView() {
        list.adapter = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance() = FragmentScreen { TransactionsFilteredFragment() }
    }
}