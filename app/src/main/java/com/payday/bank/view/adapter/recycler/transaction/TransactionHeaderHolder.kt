package com.payday.bank.view.adapter.recycler.transaction

import android.view.ViewGroup
import com.payday.bank.R
import com.payday.bank.view.adapter.recycler.base.BaseViewHolder
import com.payday.bank.view.entity.TransactionHeaderUIEntity
import kotlinx.android.synthetic.main.holder_transaction_heaeder.dateTextView

class TransactionHeaderHolder(parent: ViewGroup) :
    BaseViewHolder<TransactionHeaderUIEntity>(parent, R.layout.holder_transaction_heaeder) {

    override fun bind(entity: TransactionHeaderUIEntity) {
        super.bind(entity)
        dateTextView.text = entity.date
    }
}