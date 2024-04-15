package com.jess.nbcamp.challnge3.network

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {

    override fun intercept(
        chain: Interceptor.Chain
    ): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader(
                "Authorization",
                "KakaoAK %s".format(
                    TODO("카카오 디벨로퍼에서 인증키를 넣어주세요.")
                )
            ).build()

        return chain.proceed(newRequest)
    }

}