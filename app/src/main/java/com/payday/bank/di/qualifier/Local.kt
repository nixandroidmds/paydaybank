package com.payday.bank.di.qualifier

import javax.inject.Qualifier

@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.VALUE_PARAMETER
)
@Qualifier
@MustBeDocumented
@Retention
annotation class Local