package com.pcodelight.joindesign.service

import com.pcodelight.joindesign.AuthHelper
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class API {
    companion object {
        private const val BASE_URL = "https://dev3-api.development.tastelabgroup.com/api/v1/"

        val instance: Retrofit
        get() {
            val token = AuthHelper.instance.getAuthToken()

            val builder = OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)

            if (token?.isNotBlank() == true) {
                builder.addInterceptor(object: Interceptor {
                    override fun intercept(chain: Interceptor.Chain): Response {
                        val request = chain.request()
                        val authorizedReq = request.newBuilder()
                            .addHeader("Authorization", "Bearer $token")
                            .build()

                        return chain.proceed(authorizedReq)
                    }

                })
            }

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}