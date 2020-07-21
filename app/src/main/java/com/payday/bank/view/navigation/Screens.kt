package com.payday.bank.view.navigation

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class FragmentScreen(private val creator: () -> Fragment) : SupportAppScreen() {

    override fun getFragment(): Fragment {
        val fragment = creator()
        screenKey = fragment.javaClass.canonicalName
        return fragment
    }
}

class ActivityScreen(private val creator: (Context) -> Intent) : SupportAppScreen() {

    override fun getActivityIntent(context: Context): Intent {
        val intent = creator(context)
        screenKey = intent.component?.toString()
        return intent
    }
}