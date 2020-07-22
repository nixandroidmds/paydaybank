package com.payday.bank.presentation.transactions

import com.hadilq.liveevent.LiveEvent
import com.payday.bank.domain.repository.AuthenticationRepository
import com.payday.bank.presentation.base.BaseViewModel
import javax.inject.Inject

class TransactionsFilteredViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : BaseViewModel() {

    val logOutLiveEvent = LiveEvent<Unit>()

    fun logout() {
        launchSafe {
            authenticationRepository.signOut()
            logOutLiveEvent.postValue(Unit)
        }
    }
}