package com.payday.bank.view.fragment.authentication

import android.os.Bundle
import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.observe
import com.google.android.material.datepicker.MaterialDatePicker
import com.payday.bank.R
import com.payday.bank.domain.entity.Gender
import com.payday.bank.presentation.authentication.AuthenticationViewModel
import com.payday.bank.view.activity.MainActivity
import com.payday.bank.view.fragment.base.BaseFragment
import com.payday.bank.view.navigation.FragmentScreen
import kotlinx.android.synthetic.main.fragment_authentication.dobEditText
import kotlinx.android.synthetic.main.fragment_authentication.dobInputLayout
import kotlinx.android.synthetic.main.fragment_authentication.emailEditText
import kotlinx.android.synthetic.main.fragment_authentication.emailInputLayout
import kotlinx.android.synthetic.main.fragment_authentication.firstNameEditText
import kotlinx.android.synthetic.main.fragment_authentication.firstNameInputLayout
import kotlinx.android.synthetic.main.fragment_authentication.genderTabLayout
import kotlinx.android.synthetic.main.fragment_authentication.lastNameEditText
import kotlinx.android.synthetic.main.fragment_authentication.lastNameInputLayout
import kotlinx.android.synthetic.main.fragment_authentication.passwordEditText
import kotlinx.android.synthetic.main.fragment_authentication.passwordInputLayout
import kotlinx.android.synthetic.main.fragment_authentication.phoneEditText
import kotlinx.android.synthetic.main.fragment_authentication.phoneInputLayout
import kotlinx.android.synthetic.main.fragment_authentication.signInButton
import kotlinx.android.synthetic.main.fragment_authentication.signInSwitchButton
import kotlinx.android.synthetic.main.fragment_authentication.signUpButton

class AuthenticationFragment : BaseFragment<AuthenticationViewModel>(R.layout.fragment_authentication),
                               DateOfBirthCallback {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        savedInstanceState?.getLong(DATE_OF_BIRTHDAY_ARG)?.let {
            viewModel.dateOfBirthdayLiveData.postValue(it)
        }

        viewModel.signUpModeEnabledLiveData.observe(viewLifecycleOwner) { signUpModeEnabled ->
            firstNameInputLayout.isVisible = signUpModeEnabled
            lastNameInputLayout.isVisible = signUpModeEnabled
            phoneInputLayout.isVisible = signUpModeEnabled
            dobInputLayout.isVisible = signUpModeEnabled
            genderTabLayout.isVisible = signUpModeEnabled
            genderTabLayout.isVisible = signUpModeEnabled
            signInSwitchButton.isVisible = signUpModeEnabled

            signInButton.isGone = signUpModeEnabled

            signUpButton.rippleColor =
                (if (signUpModeEnabled) signInButton else signInSwitchButton).rippleColor
            signUpButton.backgroundTintList =
                (if (signUpModeEnabled) signInButton else signInSwitchButton).backgroundTintList
            signUpButton.setTextColor(
                (if (signUpModeEnabled) signInButton else signInSwitchButton).textColors
            )
        }
        viewModel.dateOfBirthdayStrLiveData.observe(viewLifecycleOwner, dobEditText::setText)
        viewModel.progressLiveData.observe(viewLifecycleOwner) { progress ->
            signInButton.isEnabled = !progress
            signInSwitchButton.isEnabled = !progress
            signUpButton.isEnabled = !progress
        }
        viewModel.emailErrorVisibleLiveData.observe(viewLifecycleOwner) {
            emailInputLayout.error = if (it == ResourcesCompat.ID_NULL) null else getString(it)
        }
        viewModel.passwordErrorVisibleLiveData.observe(viewLifecycleOwner) {
            passwordInputLayout.error = if (it == ResourcesCompat.ID_NULL) null else getString(it)
        }
        viewModel.firstNameErrorVisibleLiveData.observe(viewLifecycleOwner) {
            firstNameInputLayout.error = if (it == ResourcesCompat.ID_NULL) null else getString(it)
        }
        viewModel.lastNameErrorVisibleLiveData.observe(viewLifecycleOwner) {
            lastNameInputLayout.error = if (it == ResourcesCompat.ID_NULL) null else getString(it)
        }
        viewModel.phoneErrorVisibleLiveData.observe(viewLifecycleOwner) {
            phoneInputLayout.error = if (it == ResourcesCompat.ID_NULL) null else getString(it)
        }
        viewModel.dateOfBirthdayErrorVisibleLiveData.observe(viewLifecycleOwner) {
            dobInputLayout.error = if (it == ResourcesCompat.ID_NULL) null else getString(it)
        }
        viewModel.genderErrorVisibleLiveData.observe(viewLifecycleOwner) {
            if (it == ResourcesCompat.ID_NULL) {
                return@observe
            }

            onError(getString(it))
        }
        viewModel.openTransactionsScreenLiveData.observe(viewLifecycleOwner) {
            router.replaceScreen(MainActivity.newInstance())
        }

        signInButton.setOnClickListener { onSignIn() }
        signInSwitchButton.setOnClickListener { onSignIn() }
        signUpButton.setOnClickListener { onSignUp() }

        dobInputLayout.setOnClickListener { showDateOfBirthdayDialog() }
        dobEditText.setOnClickListener { showDateOfBirthdayDialog() }
    }

    private fun showDateOfBirthdayDialog() {
        MaterialDatePicker.Builder
            .datePicker()
            .setTitleText(R.string.authentication_hint_dob)
            .apply { viewModel.dateOfBirthdayLiveData.value?.let(::setSelection) }
            .build()
            .also { dialog ->
                dialog.addOnPositiveButtonClickListener { dateOfBirthday ->
                    val fragment = parentFragmentManager.findFragmentById(R.id.fragmentContainer)
                    val dateOfBirthCallback = fragment as DateOfBirthCallback?
                    dateOfBirthCallback?.onDateOfBirthCallback(dateOfBirthday)
                }
            }
            .show(
                parentFragmentManager,
                DATE_PICKER_TAG
            )
    }

    override fun onDateOfBirthCallback(dateOfBirthdayUtcMs: Long) {
        viewModel.dateOfBirthdayLiveData.postValue(dateOfBirthdayUtcMs)
    }

    private fun onSignIn() {
        viewModel.signIn(
            emailEditText.text,
            passwordEditText.text
        )
    }

    private fun onSignUp() {
        viewModel.signUp(
            emailEditText.text,
            passwordEditText.text,
            firstNameEditText.text,
            lastNameEditText.text,
            phoneEditText.text,
            Gender.cast(genderTabLayout.selectedTabPosition)
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.dateOfBirthdayLiveData.value?.let { outState.putLong(DATE_OF_BIRTHDAY_ARG, it) }
    }

    companion object {

        fun newInstance() = FragmentScreen { AuthenticationFragment() }

        private val DATE_PICKER_TAG = MaterialDatePicker::class.java.name

        private const val DATE_OF_BIRTHDAY_ARG = "DATE_OF_BIRTHDAY_ARG"
    }
}