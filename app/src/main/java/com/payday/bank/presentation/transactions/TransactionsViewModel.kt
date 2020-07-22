package com.payday.bank.presentation.transactions

import androidx.lifecycle.MutableLiveData
import com.payday.bank.data.repository.base.AccountRepository
import com.payday.bank.data.repository.base.AuthenticationRepository
import com.payday.bank.data.repository.base.TransactionRepository
import com.payday.bank.domain.entity.AccountDomainEntity
import com.payday.bank.domain.entity.TransactionDomainEntity
import com.payday.bank.presentation.base.BaseViewModel
import com.payday.bank.view.entity.BaseTransactionUIEntity
import com.payday.bank.view.entity.TransactionFilterUiEntity
import com.payday.bank.view.mapper.TransactionDomainListToUIMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TransactionsViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val accountRepository: AccountRepository,
    private val transactionRepository: TransactionRepository,
    private val transactionToUIMapper: TransactionDomainListToUIMapper
) : BaseViewModel() {

    @Volatile private var transactionDomainList = listOf<TransactionDomainEntity>()
    var transactionFilter = TransactionFilterUiEntity()
        set(value) {
            field = value

            launchSafe(onStart = { progressLiveData.postValue(true) }) {
                mapToTransactionsUiEntity()
            }.invokeOnCompletion { progressLiveData.postValue(false) }
        }

    val progressLiveData = MutableLiveData(false)

    val accountListLiveData = MutableLiveData(listOf<AccountDomainEntity>())
    val transactionUiListLiveData = MutableLiveData(listOf<BaseTransactionUIEntity>())

    init {
        refresh()
    }

    fun refresh() {
        launchSafe(onStart = { progressLiveData.postValue(true) }) {
            downloadTransactions(downloadAccounts())
        }.invokeOnCompletion { progressLiveData.postValue(false) }
    }

    private suspend fun downloadAccounts(): List<AccountDomainEntity> {
        val customerId = authenticationRepository.getToken()
        val accountList = accountRepository
            .getAccountList(customerId)
        accountListLiveData.postValue(accountList)
        return accountList
    }

    private suspend fun downloadTransactions(accountList: List<AccountDomainEntity>) {
        transactionDomainList = transactionRepository.getTransactionList(accountList)
        mapToTransactionsUiEntity()
    }

    private suspend fun mapToTransactionsUiEntity() =
        withContext(Dispatchers.Unconfined) {
            val list = transactionToUIMapper.map(transactionDomainList, transactionFilter)
            transactionUiListLiveData.postValue(list)
        }
}