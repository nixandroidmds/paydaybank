package com.payday.bank.view.activity

import android.os.Bundle
import androidx.lifecycle.observe
import com.payday.bank.R
import com.payday.bank.presentation.SplashViewModel

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