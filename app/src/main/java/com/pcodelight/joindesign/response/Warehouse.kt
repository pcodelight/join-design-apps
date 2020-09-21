package com.pcodelight.joindesign.response


import com.google.gson.annotations.SerializedName

data class Warehouse(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("uuid")
    val uuid: String
)