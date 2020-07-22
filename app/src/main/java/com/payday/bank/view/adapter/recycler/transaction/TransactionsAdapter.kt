package com.payday.bank.view.adapter.recycler.transaction

import android.view.ViewGroup
import com.payday.bank.view.adapter.recycler.base.BaseListAdapter
import com.payday.bank.view.adapter.recycler.base.BaseViewHolder
import com.payday.bank.view.entity.BaseTransactionUIEntity
import com.payday.bank.view.entity.BaseTransactionUIEntity.Companion.CATEGORY_VIEW_TYPE
import com.payday.bank.view.entity.BaseTransactionUIEntity.Companion.HEADER_VIEW_TYPE
import com.payday.bank.view.entity.BaseTransactionUIEntity.Companion.TRANSACTION_WITHOUT_DESCRIPTION_VIEW_TYPE
import com.payday.bank.view.entity.BaseTransactionUIEntity.Companion.TRANSACTION_WITH_DESCRIPTION_VIEW_TYPE

class TransactionsAdapter : BaseListAdapter<BaseTransactionUIEntity>() {

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<BaseTransactionUIEntity> =
        when (viewType) {
            HEADER_VIEW_TYPE -> TransactionHeaderHolder(parent)
            CATEGORY_VIEW_TYPE -> TransactionCategoryHolder(parent)
            TRANSACTION_WITH_DESCRIPTION_VIEW_TYPE -> TransactionHolder(parent)
            TRANSACTION_WITHOUT_DESCRIPTION_VIEW_TYPE -> TransactionHolder(parent)
            else -> throw IllegalArgumentException()
        } as BaseViewHolder<BaseTransactionUIEntity>

    override fun getItemViewType(position: Int) = getItem(position).viewType
}