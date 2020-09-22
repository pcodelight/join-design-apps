package com.pcodelight.joindesign.repo

import com.pcodelight.joindesign.model.Store
import com.pcodelight.joindesign.response.ListStoreResponse
import com.pcodelight.joindesign.response.StoreResponse
import com.pcodelight.joindesign.service.API
import com.pcodelight.joindesign.service.ApiCallback
import com.pcodelight.joindesign.service.StoreService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreRepository {
    fun getStoreDetail(authCode: String, callback: ApiCallback<Store>) {
        API.instance.create(StoreService::class.java)
            .getStore(authCode = authCode)
            .enqueue(object: Callback<StoreResponse> {
                override fun onFailure(call: Call<StoreResponse>, t: Throwable) {
                    callback.onError(t.toString())
                }

                override fun onResponse(
                    call: Call<StoreResponse>,
                    response: Response<StoreResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()?.data)
                    } else {
                        callback.onError(response.message())
                    }
                }
            })
    }

    fun getStores(callback: ApiCallback<List<Store>>) {
        API.instance.create(StoreService::class.java)
            .getStores()
            .enqueue(object: Callback<ListStoreResponse> {
                override fun onFailure(call: Call<ListStoreResponse>, t: Throwable) {
                    callback.onError(t.toString())
                }

                override fun onResponse(
                    call: Call<ListStoreResponse>,
                    response: Response<ListStoreResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body()?.data)
                    } else {
                        callback.onError(response.message())
                    }
                }
            })
    }
}