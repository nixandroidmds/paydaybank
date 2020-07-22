package com.payday.bank.util.extension

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

fun Context.getDrawableCompat(@DrawableRes resId: Int): Drawable? =
    ContextCompat.getDrawable(this, resId)

@ColorInt fun Context.getColorCompat(@ColorRes resId: Int): Int =
    ContextCompat.getColor(this, resId)

fun Resources.getDrawableCompat(
    @DrawableRes resId: Int,
    theme: Resources.Theme? = null
): Drawable? = ResourcesCompat.getDrawable(this, resId, theme)

@ColorInt fun Resources.getColorCompat(
    @ColorRes resId: Int,
    theme: Resources.Theme? = null
): Int = ResourcesCompat.getColor(this, resId, theme)