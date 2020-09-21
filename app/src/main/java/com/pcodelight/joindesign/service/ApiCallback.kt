package com.pcodelight.joindesign.service

interface ApiCallback<T> {
    fun onSuccess(data: T?)
    fun onError(error: String?)
}