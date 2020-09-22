package com.pcodelight.joindesign

import android.content.Context
import android.content.SharedPreferences
import com.pcodelight.joindesign.model.AuthData

class AuthHelper(context: Context) {
    private var token: String? = null
    private var sharedPreferences: SharedPreferences? = null

    fun getAuthToken(): String? {
        sharedPreferences?.also { sp ->
            sp.getString(Conf.ACCESS_TOKEN_KEY, "")?.takeIf { it.isNotBlank() }?.let {
                token = it
            }
        }
        return token
    }

    fun setAuthToken(authData: AuthData) {
        authData.let {
            sharedPreferences?.edit()?.apply {
                putString(Conf.ACCESS_TOKEN_KEY, it.accessToken)
                putString("refresh_token", it.refreshToken)
                putLong("expires", it.expiresIn)
                apply()
            }
        }
    }

    init {
        sharedPreferences =
            context.getSharedPreferences(Conf.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    companion object {
        lateinit var instance: AuthHelper

        fun init(context: Context) {
            instance = AuthHelper(context)
        }
    }
}