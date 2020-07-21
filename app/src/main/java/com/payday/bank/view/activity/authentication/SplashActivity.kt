package com.payday.bank.view.activity.authentication

import android.os.Bundle
import androidx.lifecycle.observe
import com.payday.bank.R
import com.payday.bank.presentation.authentication.SplashViewModel
import com.payday.bank.view.activity.MainActivity
import com.payday.bank.view.activity.base.BaseActivity

class SplashActivity : BaseActivity<SplashViewModel>(R.layout.activity_splash) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.openMainScreenLiveEvent.observe(this) {
            router.replaceScreen(MainActivity.newInstance())
        }
        viewModel.openAuthenticationScreenLiveEvent.observe(this) {
            router.replaceScreen(AuthenticationActivity.newInstance())
        }
    }
}