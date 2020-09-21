package com.pcodelight.joindesign.service

import com.pcodelight.joindesign.response.Store
import retrofit2.Call
import retrofit2.http.GET

interface StoreService {
    @GET("/stores")
    fun getStores(): Call<Store>
}