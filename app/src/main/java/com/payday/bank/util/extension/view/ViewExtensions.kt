package com.payday.bank.util.extension.view

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.payday.bank.util.extension.layoutInflater

inline val View.layoutInflater get() = context.layoutInflater

fun ViewGroup.inflateView(@LayoutRes layoutId: Int, attachToRoot: Boolean = false): View =
    layoutInflater.inflate(layoutId, this, attachToRoot)