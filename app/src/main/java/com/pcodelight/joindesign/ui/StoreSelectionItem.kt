package com.pcodelight.joindesign.ui

import android.view.View
import android.widget.RadioButton
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import com.pcodelight.joindesign.R
import com.pcodelight.joindesign.ext.fromHtml
import com.pcodelight.joindesign.model.Store
import kotlinx.android.synthetic.main.ui_store_selection_item.view.*

class StoreSelectionItem(
    var store: Store,
    var checked: Boolean,
    var onCheckListener: () -> Unit
) : AbstractItem<StoreSelectionItem.ViewHolder>() {
    class ViewHolder(
        private val view: View
    ) : FastAdapter.ViewHolder<StoreSelectionItem>(view) {

        override fun bindView(item: StoreSelectionItem, payloads: List<Any>) {
            item.store.let { store ->
                view.tvTitle.text = store.name

                store.description?.takeIf { it.isNotBlank() }?.let {
                    view.tvDescription.visibility = View.VISIBLE
                    view.tvDescription.text = store.description.fromHtml()
                } ?: view.tvDescription.apply {
                    visibility = View.GONE
                }

                view.tvOnline.visibility = if (store.isOnline) {
                    View.VISIBLE
                } else {
                    View.GONE
                }

                view.rbCheck.isChecked = item.checked
                view.rbCheck.setOnCheckedChangeListener { _, _ ->
                    onItemSelected(item)
                }

                view.setOnClickListener {
                    onItemSelected(item)
                }
            }
        }

        private fun onItemSelected(item: StoreSelectionItem) {
            if (!item.checked) {
                item.onCheckListener()
            }
        }

        override fun unbindView(item: StoreSelectionItem) {}
    }

    override val layoutRes: Int
        get() = R.layout.ui_store_selection_item
    override val type: Int
        get() = StoreSelectionItem::class.java.hashCode()

    override fun getViewHolder(v: View): ViewHolder = ViewHolder(v)
}