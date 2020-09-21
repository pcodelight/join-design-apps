package com.pcodelight.joindesign.service

import com.pcodelight.joindesign.response.ListMaterial
import com.pcodelight.joindesign.response.RawMaterial
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MaterialService {
    @GET("/raw-materials")
    fun getMaterials(
        @Query("storeId")
        storeId: String,
        @Query("page")
        page: Int
    ): Call<ListMaterial>

    @GET("/raw-materials/{id}")
    fun getMaterialDetail(
        @Path("id")
        id: String
    ): Call<RawMaterial>
}
