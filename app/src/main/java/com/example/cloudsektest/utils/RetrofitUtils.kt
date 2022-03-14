package com.example.cloudsektest.utils

import com.example.cloudsektest.utils.GsonConverter.Companion.fromJson
import retrofit2.Response

inline fun <reified T> Response<T>.getResponse(): T {
    val responseBody = body()
    return if (this.isSuccessful && responseBody != null) {
        responseBody
    } else {
        errorBody()!!.string().fromJson()!!    }
}
