package com.payday.bank.view.activity

import android.content.Intent
import android.os.Bundle
import com.payday.bank.R
import com.payday.bank.presentation.EmptyViewModel
import com.payday.bank.view.fragment.AuthenticationFragment
import com.payday.bank.view.navigation.ActivityScreen

class AuthenticationActivity : BaseActivity<EmptyViewModel>(R.layout.activity_authentication) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            router.newRootScreen(AuthenticationFragment.newInstance())
        }
    }

    companion object {

        fun newInstance() = ActivityScreen { Intent(it, AuthenticationActivity::class.java) }
    }
}