package com.pcodelight.joindesign.service

import com.pcodelight.joindesign.response.AuthData
import retrofit2.Call
import retrofit2.http.POST

interface AuthService {
    @POST("/oauth/token")
    fun getAuth(
        grantType: String = "password",
        clientId: Int,
        clientSecret: String,
        username: String,
        password: String
    ): Call<AuthData>
}