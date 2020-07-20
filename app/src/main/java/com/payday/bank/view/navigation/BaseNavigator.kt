package com.payday.bank.view.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.payday.bank.R
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command

abstract class BaseNavigator(
    activity: FragmentActivity,
    @IdRes containerId: Int = R.id.fragmentContainer
) : SupportAppNavigator(activity, activity.supportFragmentManager, containerId) {

    val topFragment get() = fragmentManager.findFragmentById(containerId)

    override fun setupFragmentTransaction(
        command: Command,
        currentFragment: Fragment?,
        nextFragment: Fragment?,
        fragmentTransaction: FragmentTransaction
    ) {
        fragmentTransaction.setCustomAnimations(
            android.R.anim.fade_in,
            android.R.anim.fade_out,
            android.R.anim.fade_in,
            android.R.anim.fade_out
        )
    }
}