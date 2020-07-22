package com.payday.bank.view.entity

import androidx.annotation.ColorInt

sealed class BaseTransactionUIEntity(val viewType: Int) {

    companion object {
        const val HEADER_VIEW_TYPE = 0
        const val CATEGORY_VIEW_TYPE = 1
        const val TRANSACTION_WITH_DESCRIPTION_VIEW_TYPE = 2
        const val TRANSACTION_WITHOUT_DESCRIPTION_VIEW_TYPE = 3
    }
}

data class TransactionUIEntity(
    @ColorInt val amountColor: Int,
    val sourceOrDestination: String,
    val category: String,
    val amount: String,
    val date: String,
    val shortDescription: String?
) : BaseTransactionUIEntity(
    viewType = if (shortDescription == null) {
        TRANSACTION_WITHOUT_DESCRIPTION_VIEW_TYPE
    } else {
        TRANSACTION_WITH_DESCRIPTION_VIEW_TYPE
    }
)

data class TransactionCategoryUIEntity(
    @ColorInt val amountColor: Int,
    val amount: Double,
    val amountStr: String,
    val category: String
) : BaseTransactionUIEntity(CATEGORY_VIEW_TYPE)

data class TransactionHeaderUIEntity(
    val dateFrom: String,
    val dateTo: String
) : BaseTransactionUIEntity(HEADER_VIEW_TYPE) {

    val date = "$dateFrom - $dateTo"
}