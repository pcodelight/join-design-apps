package com.pcodelight.joindesign.model


import com.google.gson.annotations.SerializedName

data class RawMaterial(
    @SerializedName("active")
    val active: Int,
    @SerializedName("categories")
    val categories: List<Any>,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("defaultPrice")
    val defaultPrice: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("nameChin")
    val nameChin: Any,
    @SerializedName("nameEng")
    val nameEng: String,
    @SerializedName("packing")
    val packing: Any,
    @SerializedName("sku")
    val sku: String,
    @SerializedName("stores")
    val stores: List<Store>,
    @SerializedName("supplier")
    val supplier: Supplier,
    @SerializedName("unit")
    val unit: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("updatedBy")
    val updatedBy: UpdatedBy,
    @SerializedName("uuid")
    val uuid: String,
    @SerializedName("warehouses")
    val warehouses: List<Warehouse>
)