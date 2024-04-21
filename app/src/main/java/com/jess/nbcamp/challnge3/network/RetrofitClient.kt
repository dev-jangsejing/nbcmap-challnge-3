package com.jess.nbcamp.challnge3.network

import com.google.gson.GsonBuilder
import com.jess.nbcamp.challnge3.data.remote.SearchRemoteDatasource
import com.jess.nbcamp.challnge3.network.interceptor.AuthorizationInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://dapi.kakao.com"

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor())
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(dataFormatGsonBuilder))
            .build()
    }

    private val dataFormatGsonBuilder
        get() = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create()

    val search: SearchRemoteDatasource by lazy {
        retrofit.create(SearchRemoteDatasource::class.java)
    }
}