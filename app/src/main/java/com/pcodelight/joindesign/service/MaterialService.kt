package com.pcodelight.joindesign.service

import com.pcodelight.joindesign.response.ListMaterialResponse
import com.pcodelight.joindesign.model.RawMaterial
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MaterialService {
    @GET("raw-materials")
    fun getMaterials(
        @Query("storeId")
        storeId: String,
        @Query("page")
        page: Int,
        @Query("keyword")
        keyword: String
    ): Call<ListMaterialResponse>

    @GET("raw-materials/{id}")
    fun getMaterialDetail(
        @Path("id")
        id: String
    ): Call<RawMaterial>
}
