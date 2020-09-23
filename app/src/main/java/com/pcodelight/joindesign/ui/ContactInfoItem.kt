package com.pcodelight.joindesign.ui

import android.view.View
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import com.pcodelight.joindesign.R
import com.pcodelight.joindesign.model.Supplier
import kotlinx.android.synthetic.main.ui_contact_info.view.*

class ContactInfoItem(val supplier: Supplier): AbstractItem<ContactInfoItem.ViewHolder>() {
    override val layoutRes: Int
        get() = R.layout.ui_contact_info
    override val type: Int
        get() = ContactInfoItem::class.java.hashCode()

    override fun getViewHolder(v: View) = ViewHolder(v)

    class ViewHolder(val view: View): FastAdapter.ViewHolder<ContactInfoItem>(view) {
        override fun bindView(item: ContactInfoItem, payloads: List<Any>) {
            view.apply {
                tvFullName.text = item.supplier.name
                tvAddress.text = item.supplier.address
                tvContactName.text = item.supplier.contactName
                tvPhone.text = item.supplier.tel
            }
        }

        override fun unbindView(item: ContactInfoItem) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}