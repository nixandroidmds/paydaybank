package com.payday.bank.presentation.transactions

import androidx.lifecycle.MutableLiveData
import com.payday.bank.domain.entity.AccountDomainEntity
import com.payday.bank.domain.entity.TransactionDomainEntity
import com.payday.bank.domain.repository.AccountRepository
import com.payday.bank.domain.repository.AuthenticationRepository
import com.payday.bank.domain.repository.TransactionRepository
import com.payday.bank.presentation.base.BaseViewModel
import com.payday.bank.view.entity.BaseTransactionUIEntity
import com.payday.bank.view.entity.TransactionFilterUiEntity
import com.payday.bank.view.mapper.TransactionDomainListToUIMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class TransactionsViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val accountRepository: AccountRepository,
    private val transactionRepository: TransactionRepository,
    private val transactionToUIMapper: TransactionDomainListToUIMapper
) : BaseViewModel() {

    private var job: Job? = null

    @Volatile private var transactionDomainList = listOf<TransactionDomainEntity>()
    val transactionFilterLiveData =
        object : MutableLiveData<TransactionFilterUiEntity>(TransactionFilterUiEntity()) {
            override fun setValue(value: TransactionFilterUiEntity?) {
                super.setValue(value)
                job?.cancel()
                job = launchSafe(onStart = { progressLiveData.postValue(true) }) {
                    mapToTransactionsUiEntity()
                }.apply { invokeOnCompletion { progressLiveData.postValue(false) } }
            }
        }

    val progressLiveData = MutableLiveData(false)

    val accountListLiveData = MutableLiveData(listOf<AccountDomainEntity>())
    val transactionUiListLiveData = MutableLiveData(listOf<BaseTransactionUIEntity>())

    init {
        refresh()
    }

    fun refresh() {
        job?.cancel()
        job = launchSafe(onStart = { progressLiveData.postValue(true) }) {
            downloadData()
        }.apply { invokeOnCompletion { progressLiveData.postValue(false) } }
    }

    private suspend fun downloadData() {
        val customerId = authenticationRepository.getToken()
        val accountList = accountRepository.getAccountList(customerId)
        val oldAccountList = accountListLiveData.value
        accountListLiveData.postValue(accountList)

        transactionDomainList = transactionRepository.getTransactionList(accountList)

        if (oldAccountList.isNullOrEmpty()) {
            transactionFilterLiveData.postValue(
                transactionFilterLiveData.value?.copy(
                    ibanList = accountList.mapNotNull(AccountDomainEntity::iban)
                )
            )
        }

        mapToTransactionsUiEntity()
    }

    private suspend fun mapToTransactionsUiEntity() {
        withContext(Dispatchers.Unconfined) {
            val filterEntity = transactionFilterLiveData.value
            if (filterEntity == null) {
                Timber.e("filter is null")
            } else {
                val list = transactionToUIMapper.map(transactionDomainList, filterEntity)
                transactionUiListLiveData.postValue(list)
            }
        }
    }
}