package com.pcodelight.joindesign.repo

import com.pcodelight.joindesign.model.RawMaterial
import com.pcodelight.joindesign.response.ListMaterialResponse
import com.pcodelight.joindesign.response.MaterialResponse
import com.pcodelight.joindesign.service.API
import com.pcodelight.joindesign.service.ApiCallback
import com.pcodelight.joindesign.service.MaterialService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MaterialRepository {
    fun getMaterial(uuid: String, callback: ApiCallback<RawMaterial>) {
        API.instance.create(MaterialService::class.java)
            .getMaterialDetail(uuid)
            .enqueue(object : Callback<MaterialResponse> {
                override fun onFailure(call: Call<MaterialResponse>, t: Throwable) {
                    callback.onError(t.toString())
                }

                override fun onResponse(
                    call: Call<MaterialResponse>,
                    response: Response<MaterialResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()?.data)
                    } else {
                        callback.onError("Something wrong")
                    }
                }
            })
    }

    fun getMaterials(selectedStoreUUID: String, keyword: String, page: Int, callback: ApiCallback<List<RawMaterial>>) {
        API.instance.create(MaterialService::class.java)
            .getMaterials(selectedStoreUUID, page, keyword)
            .enqueue(object : Callback<ListMaterialResponse> {
                override fun onFailure(call: Call<ListMaterialResponse>, t: Throwable) {
                    callback.onError(t.toString())
                }

                override fun onResponse(
                    call: Call<ListMaterialResponse>,
                    response: Response<ListMaterialResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()?.data)
                    } else {
                        callback.onError("Something wrong")
                    }
                }
            })
    }
}