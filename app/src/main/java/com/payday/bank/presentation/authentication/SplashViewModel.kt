package com.payday.bank.presentation.authentication

import com.hadilq.liveevent.LiveEvent
import com.payday.bank.data.repository.base.AuthenticationRepository
import com.payday.bank.presentation.base.BaseViewModel
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : BaseViewModel() {

    val openMainScreenLiveEvent = LiveEvent<Unit>()
    val openAuthenticationScreenLiveEvent = LiveEvent<Unit>()

    init {
        launchSafe {
            if (authenticationRepository.hasToken()) {
                openMainScreenLiveEvent
            } else {
                openAuthenticationScreenLiveEvent
            }.postValue(Unit)
        }
    }
}