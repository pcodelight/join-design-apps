package com.pcodelight.joindesign.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Store(
    @SerializedName("authCode")
    val authCode: String,
    @SerializedName("cuisine")
    val cuisine: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("isOnline")
    val isOnline: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("order_prefix")
    val orderPrefix: String,
    @SerializedName("previewUrl")
    val previewUrl: String?,
    @SerializedName("tenant")
    val tenant: Tenant,
    @SerializedName("uuid")
    val uuid: String,
    @SerializedName("warehouse")
    val warehouse: Warehouse
): Serializable