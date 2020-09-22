package com.pcodelight.joindesign.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

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
): Serializable