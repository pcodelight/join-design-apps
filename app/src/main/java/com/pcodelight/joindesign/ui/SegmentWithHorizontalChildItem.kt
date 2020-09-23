package com.pcodelight.joindesign.ui

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import com.pcodelight.joindesign.R
import kotlinx.android.synthetic.main.ui_segment_with_child_item.view.*

class SegmentWithHorizontalChildItem(
    val title: String,
    val childItem: List<AbstractItem<*>>
) : AbstractItem<SegmentWithHorizontalChildItem.ViewHolder>() {
    override val layoutRes: Int
        get() = R.layout.ui_segment_with_child_item
    override val type: Int
        get() = SegmentWithHorizontalChildItem::class.java.hashCode()

    override fun getViewHolder(v: View) = ViewHolder(v)

    class ViewHolder(val view: View) : FastAdapter.ViewHolder<SegmentWithHorizontalChildItem>(view) {
        private val layoutManager by lazy {
            LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        }

        private val itemAdapter by lazy {
            ItemAdapter<AbstractItem<*>>()
        }

        private val adapter by lazy {
            FastAdapter.with(itemAdapter)
        }

        override fun bindView(item: SegmentWithHorizontalChildItem, payloads: List<Any>) {
            view.apply {
                rvChild.layoutManager = layoutManager
                rvChild.adapter = adapter
                tvTitle.text = item.title
            }
            itemAdapter.set(item.childItem)
        }

        override fun unbindView(item: SegmentWithHorizontalChildItem) {
            view.rvChild.adapter = null
        }
    }

}