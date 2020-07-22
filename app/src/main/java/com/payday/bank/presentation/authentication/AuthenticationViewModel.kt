package com.payday.bank.presentation.authentication

import android.text.Editable
import androidx.core.content.res.ResourcesCompat.ID_NULL
import androidx.core.util.PatternsCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.hadilq.liveevent.LiveEvent
import com.payday.bank.R
import com.payday.bank.domain.entity.Gender
import com.payday.bank.domain.entity.UserDomainEntity
import com.payday.bank.domain.repository.AuthenticationRepository
import com.payday.bank.presentation.base.BaseViewModel
import com.payday.bank.util.date.DateUtils
import com.payday.bank.util.string.StringPattern
import java.time.Instant
import java.time.ZoneOffset
import javax.inject.Inject

class AuthenticationViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : BaseViewModel() {

    val openTransactionsScreenLiveData = LiveEvent<Unit>()

    val progressLiveData = MutableLiveData(false)
    val signUpModeEnabledLiveData = MutableLiveData(false)

    val emailErrorVisibleLiveData = MutableLiveData(ID_NULL)
    val passwordErrorVisibleLiveData = MutableLiveData(ID_NULL)
    val firstNameErrorVisibleLiveData = MutableLiveData(ID_NULL)
    val lastNameErrorVisibleLiveData = MutableLiveData(ID_NULL)
    val phoneErrorVisibleLiveData = MutableLiveData(ID_NULL)
    val dateOfBirthdayErrorVisibleLiveData = MutableLiveData(ID_NULL)
    val genderErrorVisibleLiveData = MutableLiveData(ID_NULL)

    val dateOfBirthdayLiveData = MutableLiveData<Long>()
    val dateOfBirthdayStrLiveData =
        Transformations.map(dateOfBirthdayLiveData, DateUtils::formatToDate)

    fun signIn(
        emailEditable: Editable?,
        passwordEditable: Editable?
    ) {
        if (signUpModeEnabledLiveData.value == true) {
            signUpModeEnabledLiveData.postValue(false)
            passwordErrorVisibleLiveData.postValue(ID_NULL)
        } else {
            launchSafe(onStart = { progressLiveData.postValue(true) }) {
                val email = emailEditable?.toString()?.trim()
                val password = passwordEditable?.toString()

                val emailValid = isEmailValid(email)
                val passwordValid = !password.isNullOrEmpty()

                val emailErrorRes =
                    if (emailValid) ID_NULL else R.string.authentication_error_email
                val passwordErrorRes =
                    if (passwordValid) ID_NULL else R.string.authentication_error_sign_in_password

                emailErrorVisibleLiveData.postValue(emailErrorRes)
                passwordErrorVisibleLiveData.postValue(passwordErrorRes)

                if (emailValid && passwordValid) {
                    authenticationRepository.signIn(
                        UserDomainEntity(
                            email = email,
                            password = password
                        )
                    )
                    openTransactionsScreenLiveData.postValue(Unit)
                }
            }.invokeOnCompletion { progressLiveData.postValue(false) }
        }
    }

    fun signUp(
        emailEditable: Editable?,
        passwordEditable: Editable?,
        firstNameEditable: Editable?,
        lastNameEditable: Editable?,
        phoneEditable: Editable?,
        gender: Gender?
    ) {
        if (signUpModeEnabledLiveData.value == false) {
            signUpModeEnabledLiveData.postValue(true)
            passwordErrorVisibleLiveData.postValue(ID_NULL)
        } else {
            launchSafe {
                progressLiveData.postValue(true)

                val email = emailEditable?.toString()?.trim()
                val password = passwordEditable?.toString()
                val firstName = firstNameEditable?.toString()?.trim()
                val lastName = lastNameEditable?.toString()?.trim()
                val phone = phoneEditable?.toString()?.trim()
                val dateOfBirthday = dateOfBirthdayLiveData.value
                val dateOfBirthdayInstant = dateOfBirthday?.let(Instant::ofEpochMilli)

                val emailValid = isEmailValid(email)
                val passwordValid = isPasswordValid(password)
                val firstNameValid = !firstName.isNullOrEmpty()
                val lastNameValid = !lastName.isNullOrEmpty()
                val phoneValid = !phone.isNullOrEmpty()
                val dateOfBirthdayValid = dateOfBirthdayInstant != null
                val genderValid = gender != null

                val emailErrorRes =
                    if (emailValid) ID_NULL else R.string.authentication_error_email
                val passwordErrorRes =
                    if (passwordValid) ID_NULL else R.string.authentication_error_sign_up_password
                val firstNameErrorRes =
                    if (firstNameValid) ID_NULL else R.string.authentication_error_first_name
                val lastNameErrorRes =
                    if (lastNameValid) ID_NULL else R.string.authentication_error_last_name
                val phoneErrorRes =
                    if (phoneValid) ID_NULL else R.string.authentication_error_phone
                val dateOfBirthdayErrorRes =
                    if (dateOfBirthdayValid) ID_NULL else R.string.authentication_error_dob
                val genderErrorRes =
                    if (genderValid) ID_NULL else R.string.authentication_error_gender

                emailErrorVisibleLiveData.postValue(emailErrorRes)
                passwordErrorVisibleLiveData.postValue(passwordErrorRes)
                firstNameErrorVisibleLiveData.postValue(firstNameErrorRes)
                lastNameErrorVisibleLiveData.postValue(lastNameErrorRes)
                phoneErrorVisibleLiveData.postValue(phoneErrorRes)
                dateOfBirthdayErrorVisibleLiveData.postValue(dateOfBirthdayErrorRes)
                genderErrorVisibleLiveData.postValue(genderErrorRes)

                if (emailValid &&
                    passwordValid &&
                    firstNameValid &&
                    lastNameValid &&
                    phoneValid &&
                    dateOfBirthdayValid &&
                    genderValid
                ) {
                    authenticationRepository.signUp(
                        UserDomainEntity(
                            firstName = firstName,
                            lastName = lastName,
                            gender = gender,
                            email = email,
                            password = password,
                            zonedDateTime = dateOfBirthdayInstant?.atZone(ZoneOffset.UTC),
                            phone = phone
                        )
                    )
                    openTransactionsScreenLiveData.postValue(Unit)
                }
            }.invokeOnCompletion { progressLiveData.postValue(false) }
        }
    }

    private fun isEmailValid(email: String?) =
        !email.isNullOrEmpty() && PatternsCompat.EMAIL_ADDRESS.toRegex().matches(email)

    private fun isPasswordValid(password: String?) =
        password != null && password.length > 5 && StringPattern.PASSWORD.matches(password)
}