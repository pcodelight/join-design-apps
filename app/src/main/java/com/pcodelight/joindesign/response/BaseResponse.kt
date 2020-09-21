package com.pcodelight.joindesign.response

import java.io.Serializable

abstract class BaseResponse<T> : Serializable {
    val data: T? = null
    val error: Boolean = false
    val message: String = ""
}