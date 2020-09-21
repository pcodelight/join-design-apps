package com.pcodelight.joindesign.response

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class AuthData(
    @SerializedName("budi")
    val tokenType: String,
    @SerializedName("expires_in")
    val expiresIn: Long,
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String
) : Serializable