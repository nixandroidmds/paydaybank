package com.payday.bank.view.mapper

import android.content.Context
import com.payday.bank.R
import com.payday.bank.domain.entity.TransactionDomainEntity
import com.payday.bank.util.date.DateUtils
import com.payday.bank.util.extension.getColorCompat
import com.payday.bank.view.entity.BaseTransactionUIEntity
import com.payday.bank.view.entity.TransactionCategoryUIEntity
import com.payday.bank.view.entity.TransactionFilterUiEntity
import com.payday.bank.view.entity.TransactionHeaderUIEntity
import com.payday.bank.view.entity.TransactionUIEntity
import java.text.DecimalFormat
import java.util.Collections
import javax.inject.Inject

class TransactionDomainListToUIMapper @Inject constructor(
    private val context: Context
) {

    private val unknown by lazy { context.getString(R.string.unknown) }

    fun map(
        list: List<TransactionDomainEntity>,
        filterEntity: TransactionFilterUiEntity
    ): List<BaseTransactionUIEntity> {
        val positiveAmountColor = context.getColorCompat(R.color.transactionsFilteredPositive)
        val negativeAmountColor = context.getColorCompat(R.color.transactionsFilteredNegative)

        val dateFrom = filterEntity.dateFrom
        val dateTo = filterEntity.dateTo
        val dateFromStr = DateUtils.formatToUserDate(filterEntity.dateFrom) ?: ""
        val dateToStr = DateUtils.formatToUserDate(dateTo) ?: ""

        val filteredList = list
            .asSequence()
            .filter { filterEntity.ibanList.contains(it.account?.iban) }
            .filter { it.dateTime != null && it.dateTime in dateFrom..dateTo }
            .filter {
                when (it.account?.active) {
                    true -> filterEntity.activeVisible
                    false -> true
                    else -> true
                }
            }.filter {
                when (it.account?.active) {
                    true -> true
                    false -> filterEntity.inactiveVisible
                    else -> filterEntity.inactiveVisible
                }
            }.toList()

        val headerList = listOf(TransactionHeaderUIEntity(dateFromStr, dateToStr))
        val categoryList = filteredList
            .groupBy(TransactionDomainEntity::category)
            .flatMap { (category, categorizedList) ->
                val amount = categorizedList.fold(0.0) { amount, entity -> amount + entity.amount }
                val amountStr = DECIMAL_FORMAT.format(amount)
                val prefixAmount = if (amount > 0) "+" else ""

                TransactionCategoryUIEntity(
                    amountColor = if (amount < 0.0) negativeAmountColor else positiveAmountColor,
                    amount = amount,
                    amountStr = "$prefixAmount$amountStr",
                    category = category ?: unknown
                ).let(Collections::singleton)
            }.sortedByDescending(TransactionCategoryUIEntity::amount)

        val transactionEntityList = filteredList
            .asSequence()
            .sortedByDescending { it.dateTime?.toInstant()?.toEpochMilli() ?: 0 }
            .map {
                val prefixAmount = if (it.amount > 0.0) "+" else ""

                TransactionUIEntity(
                    amountColor = if (it.amount < 0.0) negativeAmountColor else positiveAmountColor,
                    sourceOrDestination = it.vendor ?: unknown,
                    category = it.category ?: unknown,
                    amount = "$prefixAmount${DECIMAL_FORMAT.format(it.amount)}",
                    date = DateUtils.formatToUserDateTime(it.dateTime) ?: "",
                    shortDescription = getDescription(it)
                )
            }.toList()

        return if (transactionEntityList.isEmpty()) listOf() else headerList +
                categoryList +
                transactionEntityList
    }

    private fun getDescription(entity: TransactionDomainEntity): String? {
        val anonymousIban = entity.account?.anonymousIban
        return if (anonymousIban.isNullOrEmpty()) {
            entity.account?.type
        } else {
            val type = entity.account.type?.let { type -> " ($type)" } ?: ""
            "$anonymousIban${type}"
        }
    }

    companion object {

        private val DECIMAL_FORMAT = DecimalFormat("##.00")
    }
}