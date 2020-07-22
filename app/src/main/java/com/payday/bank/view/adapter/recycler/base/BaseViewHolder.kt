package com.payday.bank.view.adapter.recycler.base

import android.content.Context
import android.content.res.Resources
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.payday.bank.util.extension.view.inflateView
import kotlinx.android.extensions.LayoutContainer

open class BaseViewHolder<T : Any>(parent: ViewGroup, @LayoutRes resId: Int) :
    RecyclerView.ViewHolder(parent.inflateView(resId)), LayoutContainer {

    override val containerView get() = itemView

    protected val context: Context get() = itemView.context

    protected val resources: Resources get() = context.resources

    lateinit var entity: T
        private set

    @CallSuper open fun bind(entity: T) {
        this.entity = entity
    }
}