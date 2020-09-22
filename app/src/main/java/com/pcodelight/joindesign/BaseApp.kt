package com.pcodelight.joindesign

import android.app.Application

class BaseApp : Application() {
    override fun onCreate() {
        super.onCreate()

        AuthHelper.init(this)
    }
}