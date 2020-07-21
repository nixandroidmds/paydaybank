package com.payday.bank.view.activity.authentication

import android.content.Intent
import android.os.Bundle
import com.payday.bank.R
import com.payday.bank.presentation.base.EmptyViewModel
import com.payday.bank.view.activity.base.BaseActivity
import com.payday.bank.view.fragment.authentication.AuthenticationFragment
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