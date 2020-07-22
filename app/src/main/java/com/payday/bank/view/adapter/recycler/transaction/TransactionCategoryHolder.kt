package com.payday.bank.view.adapter.recycler.transaction

import android.view.ViewGroup
import com.payday.bank.R
import com.payday.bank.view.adapter.recycler.base.BaseViewHolder
import com.payday.bank.view.entity.TransactionCategoryUIEntity
import kotlinx.android.synthetic.main.holder_transaction_category.categoryTextView
import kotlinx.android.synthetic.main.holder_transaction_category.sumBalanceTextView

class TransactionCategoryHolder(parent: ViewGroup) :
    BaseViewHolder<TransactionCategoryUIEntity>(parent, R.layout.holder_transaction_category) {

    override fun bind(entity: TransactionCategoryUIEntity) {
        super.bind(entity)
        categoryTextView.text = entity.category
        sumBalanceTextView.setTextColor(entity.amountColor)
        sumBalanceTextView.text = entity.amountStr
    }
}