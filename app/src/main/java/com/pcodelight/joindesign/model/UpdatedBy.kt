package com.pcodelight.joindesign.model


import com.google.gson.annotations.SerializedName

data class UpdatedBy(
    @SerializedName("active")
    val active: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("hkid")
    val hkid: Any,
    @SerializedName("hourlyWage")
    val hourlyWage: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("monthlySalary")
    val monthlySalary: Any,
    @SerializedName("name")
    val name: String,
    @SerializedName("tel")
    val tel: Any,
    @SerializedName("uuid")
    val uuid: String
)