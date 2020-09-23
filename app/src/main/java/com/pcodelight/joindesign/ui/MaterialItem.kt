package com.pcodelight.joindesign.ui

import android.view.View
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.items.AbstractItem
import com.pcodelight.joindesign.R
import com.pcodelight.joindesign.model.RawMaterial
import kotlinx.android.synthetic.main.ui_material_item.view.*

class MaterialItem(
    private val materialData: RawMaterial,
    private val onClickListener: (String) -> Unit
): AbstractItem<MaterialItem.ViewHolder>() {

    override val layoutRes: Int
        get() = R.layout.ui_material_item
    override val type: Int
        get() = MaterialItem::class.java.hashCode()

    override fun getViewHolder(v: View): ViewHolder = ViewHolder(v)

    class ViewHolder(private val view: View): FastAdapter.ViewHolder<MaterialItem>(view) {
        override fun bindView(item: MaterialItem, payloads: List<Any>) {
            view.tvName.text = item.materialData.sku
            view.tvStoreCount.text = view.context.getString(R.string.store_count, item.materialData.stores.size)
            view.tvWarehouseCount.text = view.context.getString(R.string.warehouse_count, item.materialData.warehouses.size)

            val builder = StringBuilder()
            builder.append(item.materialData.defaultPrice)
            builder.append(" / ")
            builder.append(item.materialData.unit)

            view.tvPriceUnit.text = builder

            view.setOnClickListener {
                item.onClickListener(item.materialData.uuid)
            }
        }

        override fun unbindView(item: MaterialItem) {}
    }
}