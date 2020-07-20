package com.payday.bank.util.extension

import java.lang.reflect.ParameterizedType

fun <C> Class<*>.getGenericClass(genericPosition: Int): Class<C> =
    getGenericClassOrNull(genericPosition)!!

@Suppress("UNCHECKED_CAST")
fun <C> Class<*>.getGenericClassOrNull(genericPosition: Int): Class<C>? =
    ((genericSuperclass as? ParameterizedType?)?.actualTypeArguments?.get(genericPosition) as Class<C>?)
            ?: superclass?.getGenericClassOrNull(genericPosition)