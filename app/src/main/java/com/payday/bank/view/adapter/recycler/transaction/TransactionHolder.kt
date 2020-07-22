package com.payday.bank.view.adapter.recycler.transaction

import android.view.ViewGroup
import androidx.core.view.isGone
import com.payday.bank.R
import com.payday.bank.view.adapter.recycler.base.BaseViewHolder
import com.payday.bank.view.entity.TransactionUIEntity
import kotlinx.android.synthetic.main.holder_transaction.amountTextView
import kotlinx.android.synthetic.main.holder_transaction.categoryTextView
import kotlinx.android.synthetic.main.holder_transaction.dateTextView
import kotlinx.android.synthetic.main.holder_transaction.shortDescriptionTextView
import kotlinx.android.synthetic.main.holder_transaction.sourceOrDestinationTextView

class TransactionHolder(parent: ViewGroup) :
    BaseViewHolder<TransactionUIEntity>(parent, R.layout.holder_transaction) {

    override fun bind(entity: TransactionUIEntity) {
        super.bind(entity)
        sourceOrDestinationTextView.text = entity.sourceOrDestination
        categoryTextView.text = entity.category
        dateTextView.text = entity.date

        shortDescriptionTextView.isGone = entity.shortDescription.isNullOrEmpty()
        shortDescriptionTextView.text = entity.shortDescription

        amountTextView.text = entity.amount
        amountTextView.setTextColor(entity.amountColor)
    }
}