package com.lucas.instock.di

import com.google.gson.GsonBuilder
import com.lucas.instock.data.remote.CustomApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCustomApi(): CustomApi {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl("https://www.google.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(OkHttpClient.Builder()
                .addInterceptor {
                    val request = it.request()
                    val newRequest = request.newBuilder()
                        .header("Accept", "*/*")
                        //.header("Accept-Encoding", "gzip, deflate, br")
                        .header(
                            "User-Agent",
                            "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.66 Safari/537.36"
                        ).build()
                    it.proceed(newRequest)
                }
                .build()
            )
            .build()
            .create(CustomApi::class.java)
    }

}