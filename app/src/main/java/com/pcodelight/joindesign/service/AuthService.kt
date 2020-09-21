package com.pcodelight.joindesign.service

import com.pcodelight.joindesign.model.AuthData
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("oauth/token")
    fun getAuth(@Body requestBody: RequestBody): Call<AuthData>
}