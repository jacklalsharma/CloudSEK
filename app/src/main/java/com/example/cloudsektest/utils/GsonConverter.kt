package com.example.cloudsektest.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GsonConverter {

    companion object {

        val gson = Gson()

        inline fun <reified T : Any> String.fromJson(): T? = this.let {
            val type = object : TypeToken<T>() {}.type
            gson.fromJson(this, type)
        }
    }
}