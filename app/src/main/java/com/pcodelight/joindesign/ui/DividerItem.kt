package com.pcodelight.joindesign.ui

import android.view.View
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import com.pcodelight.joindesign.R

class DividerItem: AbstractItem<DividerItem.ViewHolder>() {
    override val layoutRes: Int
        get() = R.layout.ui_divider_item
    override val type: Int
        get() = DividerItem::class.java.hashCode()

    override fun getViewHolder(v: View): ViewHolder = ViewHolder(v)

    class ViewHolder(view: View): FastAdapter.ViewHolder<DividerItem>(view) {
        override fun bindView(item: DividerItem, payloads: List<Any>) {}
        override fun unbindView(item: DividerItem) {}
    }
}