package com.pcodelight.joindesign.response


import com.google.gson.annotations.SerializedName

data class Supplier(
    @SerializedName("address")
    val address: String,
    @SerializedName("contactName")
    val contactName: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("remark")
    val remark: Any,
    @SerializedName("tel")
    val tel: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("uuid")
    val uuid: String
)