package com.lucas.instock.data.remote

import retrofit2.http.GET
import retrofit2.http.Url

interface CustomApi {

    @GET
    suspend fun getUrlContent(
        @Url url: String
    ): String
}