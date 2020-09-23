package com.pcodelight.joindesign.ui

import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import com.pcodelight.joindesign.R
import kotlinx.android.synthetic.main.ui_capsule_with_icon_item.view.*

class CapsuleWithIconLabelItem(
    private val text: String,
    @DrawableRes
    private val iconRes: Int = R.drawable.ic_warehouse,
    @DrawableRes
    private val backgroundColor: Int = R.drawable.white_bordered_background

) : AbstractItem<CapsuleWithIconLabelItem.ViewHolder>() {
    override val layoutRes: Int
        get() = R.layout.ui_capsule_with_icon_item
    override val type: Int
        get() = CapsuleWithIconLabelItem::class.java.hashCode()

    override fun getViewHolder(v: View) = ViewHolder(v)

    class ViewHolder(val view: View): FastAdapter.ViewHolder<CapsuleWithIconLabelItem>(view) {
        override fun bindView(item: CapsuleWithIconLabelItem, payloads: List<Any>) {
            view.apply {
                tvLabel.text = item.text
                ivIcon.setImageDrawable(ContextCompat.getDrawable(view.context, item.iconRes))
                background = ContextCompat.getDrawable(view.context, item.backgroundColor)
            }
        }
        override fun unbindView(item: CapsuleWithIconLabelItem) {}
    }
}