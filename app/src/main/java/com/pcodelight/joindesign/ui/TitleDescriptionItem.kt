package com.pcodelight.joindesign.ui

import android.view.View
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import com.pcodelight.joindesign.R
import com.pcodelight.joindesign.ext.fromHtml
import com.pcodelight.joindesign.ext.replaceDashIfEmpty
import kotlinx.android.synthetic.main.ui_title_description_item.view.*

class TitleDescriptionItem(
    val title: String,
    val description: String
): AbstractItem<TitleDescriptionItem.ViewHolder>() {
    override val layoutRes: Int
        get() = R.layout.ui_title_description_item
    override val type: Int
        get() = TitleDescriptionItem::class.java.hashCode()

    override fun getViewHolder(v: View) = ViewHolder(v)

    class ViewHolder(val view: View): FastAdapter.ViewHolder<TitleDescriptionItem>(view) {
        override fun bindView(item: TitleDescriptionItem, payloads: List<Any>) {
            view.apply {
                tvTitle.text = item.title
                tvDescription.text = item.description.replaceDashIfEmpty().fromHtml()
            }
        }
        override fun unbindView(item: TitleDescriptionItem) {}
    }
}