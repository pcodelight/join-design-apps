package com.pcodelight.joindesign.ui

import android.view.View
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import com.pcodelight.joindesign.R

class LoadingItem: AbstractItem<LoadingItem.ViewHolder>() {
    override val layoutRes: Int
        get() = R.layout.ui_loading_item
    override val type: Int = LoadingItem::class.java.hashCode()
    override fun getViewHolder(v: View) = ViewHolder(v)

    class ViewHolder(v: View): FastAdapter.ViewHolder<LoadingItem>(v) {
        override fun bindView(item: LoadingItem, payloads: List<Any>) {}
        override fun unbindView(item: LoadingItem) {}
    }
}