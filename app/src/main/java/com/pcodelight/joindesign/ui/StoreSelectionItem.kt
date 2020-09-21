package com.pcodelight.joindesign.ui

import android.view.View
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import com.pcodelight.joindesign.R

class StoreSelectionItem : AbstractItem<StoreSelectionItem.ViewHolder>() {
    class ViewHolder(val view: View): FastAdapter.ViewHolder<StoreSelectionItem>(view) {
        override fun bindView(item: StoreSelectionItem, payloads: List<Any>) {

        }

        override fun unbindView(item: StoreSelectionItem) {}
    }

    override val layoutRes: Int
        get() = R.layout.ui_store_selection_item
    override val type: Int
        get() = StoreSelectionItem::class.java.hashCode()
    override fun getViewHolder(v: View): ViewHolder = ViewHolder(v)
}