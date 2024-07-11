package com.sixth.space.network

import com.sixth.space.uitls.NetworkConnectivity
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response


class NetworkInterceptor(private val network: NetworkConnectivity) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        //网络没链接上直接走缓存
        request = if (!network.isConnected()){
            request.newBuilder()
                .cacheControl(CacheControl.FORCE_CACHE)
                .build()
        }else{
            request.newBuilder()
                .addHeader(
                    "User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:49.0) Gecko/20100101 Firefox/49.0"
                )
                .cacheControl(CacheControl.FORCE_NETWORK)
                .build()
        }
        val response = chain.proceed(request)
        if (!network.isConnected()){
            // 无网络时，设置超时为15天
            val maxStale = 60 * 60 * 24 * 15
            response.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                .removeHeader("Pragma")
                .build()
        }else{
            val maxAge = 0 * 60
            // 有网络时 设置缓存超时时间0个小时
            // 有网络时 设置缓存超时时间0个小时
            response.newBuilder()
                .header("Cache-Control", "public, max-age=$maxAge")
                .removeHeader("Pragma") // 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                .build()
        }
        return response
    }

}