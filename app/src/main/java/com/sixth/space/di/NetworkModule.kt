package com.sixth.space.di

import android.content.Context
import com.sixth.space.network.NetworkInterceptor
import com.sixth.space.network.RetrofitService
import com.sixth.space.uitls.NetworkConnectivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Singleton


/**
 * @author: Frankie
 * @Date: 2024/2/29
 * @Description:
 */
@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    val BASE_URL = "http://baobab.kaiyanapp.com"

    @Provides
    @Singleton
    fun provideOkHttpClient(networkInterceptor: NetworkInterceptor, cache: Cache): OkHttpClient {
        return OkHttpClient.Builder().apply {
            cache(cache)
            addInterceptor(networkInterceptor)
            addNetworkInterceptor(networkInterceptor)
        }.build()
    }

    @Singleton
    @Provides
    fun provideMovieService(okHttpClient: OkHttpClient): RetrofitService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(RetrofitService::class.java)
    }

    @Singleton
    @Provides
    fun provideNetworkInterceptor(network: NetworkConnectivity): NetworkInterceptor {
        return NetworkInterceptor(network);
    }

    @Singleton
    @Provides
    fun provideNetworkCache(@ApplicationContext app: Context): Cache {
        val mFile = File(app.cacheDir, "cache")
        val mCache = Cache(mFile, 1024 * 1024 * 200)
        return mCache;
    }
}