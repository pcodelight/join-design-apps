package com.pcodelight.joindesign.repo

import com.google.gson.Gson
import com.pcodelight.joindesign.model.AuthData
import com.pcodelight.joindesign.response.AuthDataResponse
import com.pcodelight.joindesign.service.API
import com.pcodelight.joindesign.service.ApiCallback
import com.pcodelight.joindesign.service.AuthService
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository {
    fun getAuth(username: String, password: String, callback: ApiCallback<AuthData>) {
        val requestBodyBuilder = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("username", username)
            .addFormDataPart("password", password)
            .addFormDataPart("client_id", "7")
                // todo: save client secret in secure way
            .addFormDataPart("client_secret", "7NDniuscI4542dXzaUiCTN79iIuuMNiQ0wcItmxa")
            .addFormDataPart("grant_type", "password")
            .build()


        API.instance.create(AuthService::class.java)
            .getAuth(requestBodyBuilder).enqueue(object: Callback<AuthData> {
                override fun onFailure(call: Call<AuthData>, t: Throwable) {
                    callback.onError(t.toString())
                }

                override fun onResponse(call: Call<AuthData>, response: Response<AuthData>) {
                    if (response.isSuccessful && response.body() != null) {
                        callback.onSuccess(response.body())
                    } else {
                        callback.onError("Username / password is wrong or please try again later")
                    }
                }
            })
    }
}