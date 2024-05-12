package com.jess.nbcamp.challenge3.di

import com.google.gson.GsonBuilder
import com.jess.nbcamp.challenge3.data.remote.SearchRemoteDatasource
import com.jess.nbcamp.challenge3.network.interceptor.AuthorizationInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    fun provideAuthorizationInterceptor(): Interceptor {
        return AuthorizationInterceptor()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor())
            .build()
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dapi.kakao.com")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                        .create()
                )
            )
            .build()
    }

    @Provides
    fun provideSearchRemoteDatasource(
        retrofit: Retrofit,
    ): SearchRemoteDatasource {
        return retrofit.create(SearchRemoteDatasource::class.java)
    }

}