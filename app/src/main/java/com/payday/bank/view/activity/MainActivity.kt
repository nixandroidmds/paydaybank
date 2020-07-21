package com.payday.bank.view.activity

import android.content.Intent
import com.payday.bank.R
import com.payday.bank.presentation.base.EmptyViewModel
import com.payday.bank.view.activity.base.BaseActivity
import com.payday.bank.view.navigation.ActivityScreen

class MainActivity : BaseActivity<EmptyViewModel>(R.layout.activity_main) {

    companion object {

        fun newInstance() = ActivityScreen { Intent(it, MainActivity::class.java) }
    }
}