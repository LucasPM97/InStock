package com.lucas.instock.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CustomRetrofitBuilder {

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: CustomApi = getRetrofit().create(CustomApi::class.java)
}