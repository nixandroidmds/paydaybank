package com.payday.bank.view.adapter.recycler.base

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import java.util.Objects

abstract class BaseListAdapter<T : Any>(
    areItemsTheSame: (T, T) -> Boolean = Objects::equals,
    areContentsTheSame: (T, T) -> Boolean = Objects::equals,
    callback: DiffUtil.ItemCallback<T> = DefaultDiffUtilItemCallback(
        areItemsTheSame,
        areContentsTheSame
    )
) : ListAdapter<T, BaseViewHolder<T>>(callback) {

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        currentList.getOrNull(position)?.let(holder::bind)
    }

    public override fun getItem(position: Int): T = super.getItem(position)
}