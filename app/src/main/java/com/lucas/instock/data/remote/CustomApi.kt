package com.lucas.instock.data.remote

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Url

interface CustomApi {

    @GET
    suspend fun getUrlContent(
        @Url url: String
    ): String?
}