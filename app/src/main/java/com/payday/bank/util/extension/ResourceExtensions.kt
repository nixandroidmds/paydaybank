package com.payday.bank.util.extension

import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

@ColorInt fun Context.getColorCompat(@ColorRes resId: Int): Int =
    ContextCompat.getColor(this, resId)