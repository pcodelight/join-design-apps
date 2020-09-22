package com.pcodelight.joindesign.service

import com.pcodelight.joindesign.response.ListStoreResponse
import com.pcodelight.joindesign.response.StoreResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface StoreService {
    @GET("stores")
    fun getStores(): Call<ListStoreResponse>

    @GET("stores/findByAuthCode")
    fun getStore(@Query("authCode") authCode: Long): Call<StoreResponse>
}