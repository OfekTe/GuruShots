package com.ofekte.gurushotstest.libraries.retrofit.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class BaseHeadersInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder().apply {
                addHeader("X-API-VERSION", "13")
                addHeader("X-ENV", "WEB")
                addHeader("X-TOKEN", "539a5789c36b7d3408a0aa1df73dc46d976fadaa9b85b60c9eb8c864a2803b20400b406a810cf83d2699b0d372f75d78")
                addHeader("X-Requested-With", "XMLHttpRequest")
                addHeader("TOKEN5.18.11", "SFlIbDNwU2VIRC9WUjRkd05nTTN3eFJOSy93TFpmYzNna3pqZERGVUMyMkJjSWlWVTlaN0xUUHR2bW1RY3NOWCtzM3RseGlMNWhXa0NtQTlXUkZzSlE9PQ")
                addHeader("X-APP-VERSION", "5.18.11")
            }.build()
        )
    }
}