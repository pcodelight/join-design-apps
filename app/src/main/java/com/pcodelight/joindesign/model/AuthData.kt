package com.pcodelight.joindesign.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class AuthData(
    @SerializedName("token_type")
    val tokenType: String,
    @SerializedName("expires_in")
    val expiresIn: Long,
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String
) : Serializable