package com.sixth.space.di

import com.google.gson.GsonBuilder
import com.sixth.space.data.DataRepository
import com.sixth.space.data.DataRepositorySource
import com.sixth.space.network.NetworkInterceptor
import com.sixth.space.network.RetrofitService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            val networkInterceptor = NetworkInterceptor()
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
    @Provides
    @Singleton
    fun provideDataRepository(service: RetrofitService): DataRepository{
        return DataRepository(service,Dispatchers.IO);
    }
}