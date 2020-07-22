package com.payday.bank.view.fragment.transactions

import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.lifecycle.observe
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.payday.bank.R
import com.payday.bank.presentation.base.EmptyViewModel
import com.payday.bank.presentation.transactions.TransactionsViewModel
import com.payday.bank.util.date.DateUtils
import com.payday.bank.view.fragment.base.BaseFragment
import com.payday.bank.view.navigation.FragmentScreen
import kotlinx.android.synthetic.main.fragment_filter_transactions.accountsButton
import kotlinx.android.synthetic.main.fragment_filter_transactions.activatedVisibleToggle
import kotlinx.android.synthetic.main.fragment_filter_transactions.fromDateButton
import kotlinx.android.synthetic.main.fragment_filter_transactions.inactivatedVisibleToggle
import kotlinx.android.synthetic.main.fragment_filter_transactions.toDateButton
import kotlinx.android.synthetic.main.fragment_filter_transactions.toDateResetButton
import java.time.Instant
import java.time.ZoneId

class TransactionsFilterFragment :
    BaseFragment<EmptyViewModel>(R.layout.fragment_filter_transactions) {

    override val titleRes = R.string.transactions_filter_title

    private val sharedViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get<TransactionsViewModel>()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHomeIconEnabled(true)

        val activatedCheckedChangeListener =
            CompoundButton.OnCheckedChangeListener { _, isChecked ->
                activatedVisibleToggle.setOnCheckedChangeListener(null)
                sharedViewModel.transactionFilterLiveData.postValue(
                    sharedViewModel.transactionFilterLiveData.value?.copy(activeVisible = isChecked)
                )
            }
        val inactivatedCheckedChangeListener =
            CompoundButton.OnCheckedChangeListener { _, isChecked ->
                inactivatedVisibleToggle.setOnCheckedChangeListener(null)
                sharedViewModel.transactionFilterLiveData.postValue(
                    sharedViewModel.transactionFilterLiveData.value?.copy(inactiveVisible = isChecked)
                )
            }


        sharedViewModel.transactionFilterLiveData.observe(viewLifecycleOwner) {
            fromDateButton.text = DateUtils.formatToUserDate(it.dateFrom)
            toDateButton.text = DateUtils.formatToUserDate(it.dateTo)
            activatedVisibleToggle.isChecked = it.activeVisible
            inactivatedVisibleToggle.isChecked = it.inactiveVisible
            activatedVisibleToggle.setOnCheckedChangeListener(activatedCheckedChangeListener)
            inactivatedVisibleToggle.setOnCheckedChangeListener(inactivatedCheckedChangeListener)

            toDateResetButton.isVisible = it.hasDateTo
        }

        fromDateButton.setOnClickListener {
            MaterialDatePicker.Builder
                .datePicker()
                .setTitleText(R.string.transactions_filter_date_from)
                .setSelection(
                    sharedViewModel
                        .transactionFilterLiveData
                        .value
                        ?.dateFrom
                        ?.toInstant()
                        ?.toEpochMilli()
                )
                .build()
                .also { dialog ->
                    dialog.addOnPositiveButtonClickListener { updateFrom(dialog, it) }
                }.show(parentFragmentManager, DATE_PICKER_TAG)
        }

        toDateButton.setOnClickListener {
            MaterialDatePicker.Builder
                .datePicker()
                .setTitleText(R.string.transactions_filter_date_to)
                .setSelection(
                    sharedViewModel
                        .transactionFilterLiveData
                        .value
                        ?.dateTo
                        ?.toInstant()
                        ?.toEpochMilli()
                )
                .build()
                .also { dialog ->
                    dialog.addOnPositiveButtonClickListener { updateTo(dialog, it) }
                }.show(parentFragmentManager, DATE_PICKER_TAG)
        }

        activatedVisibleToggle.setOnCheckedChangeListener(activatedCheckedChangeListener)
        inactivatedVisibleToggle.setOnCheckedChangeListener(inactivatedCheckedChangeListener)

        toDateResetButton.setOnClickListener {
            sharedViewModel.transactionFilterLiveData.postValue(
                sharedViewModel.transactionFilterLiveData.value?.copy(_dateTo = null)
            )
        }

        accountsButton.setOnClickListener {
            val transactionFilter = sharedViewModel.transactionFilterLiveData.value
            val accountList = sharedViewModel.accountListLiveData.value

            val checkedIbanList = transactionFilter?.ibanList ?: listOf()
            val ibanList = accountList?.mapNotNull { it.iban }?.toTypedArray() ?: arrayOf()

            val checkedList =
                ibanList.map(checkedIbanList::contains).toTypedArray().toBooleanArray()

            MaterialAlertDialogBuilder(requireContext())
                .setTitle(R.string.transactions_filter_accounts)
                .setMultiChoiceItems(ibanList, checkedList) { _, which, isChecked ->
                    val iban = ibanList[which]
                    val oldTransactionFilter = sharedViewModel.transactionFilterLiveData.value
                    val mutableIbanList = oldTransactionFilter?.ibanList?.toMutableList()

                    if (isChecked) {
                        mutableIbanList?.add(iban)
                    } else {
                        mutableIbanList?.remove(iban)
                    }

                    sharedViewModel.transactionFilterLiveData.postValue(
                        oldTransactionFilter?.copy(ibanList = mutableIbanList?.toList() ?: listOf())
                    )
                }.setPositiveButton(android.R.string.ok, null)
                .show()
        }
    }

    override val menuRes get() = R.menu.empty

    companion object {

        fun newInstance() = FragmentScreen { TransactionsFilterFragment() }

        private val DATE_PICKER_TAG = MaterialDatePicker::class.java.name

        private fun updateFrom(dialog: MaterialDatePicker<Long>, from: Long) {
            val fragment = dialog.parentFragmentManager.findFragmentById(R.id.fragmentContainer)
            val filterFragment = fragment as? TransactionsFilterFragment? ?: return
            val sharedViewModel = filterFragment.sharedViewModel
            sharedViewModel.transactionFilterLiveData.postValue(
                sharedViewModel.transactionFilterLiveData.value?.copy(
                    dateFrom = Instant.ofEpochMilli(from).atZone(ZoneId.systemDefault())
                )
            )
        }

        private fun updateTo(dialog: MaterialDatePicker<Long>, to: Long) {
            val fragment = dialog.parentFragmentManager.findFragmentById(R.id.fragmentContainer)
            val filterFragment = fragment as? TransactionsFilterFragment? ?: return
            val sharedViewModel = filterFragment.sharedViewModel
            sharedViewModel.transactionFilterLiveData.postValue(
                sharedViewModel.transactionFilterLiveData.value?.copy(
                    _dateTo = Instant
                        .ofEpochMilli(to)
                        .atZone(ZoneId.systemDefault())
                        .withHour(23)
                        .withMinute(59)
                )
            )
        }
    }
}