package com.sixth.space.di

import android.app.Application
import android.content.Context
import android.provider.MediaStore.Video
import androidx.room.Room
import com.sixth.space.data.dao.VideoDatabase
import com.sixth.space.data.dao.VideoRepository
import com.sixth.space.data.dao.VideoRepositoryImpl
import com.sixth.space.uitls.Network
import com.sixth.space.uitls.NetworkConnectivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

/**
 * @author: Frankie
 * @Date: 2024/3/3
 * @Description:
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideCoroutineContext(): CoroutineContext {
        return Dispatchers.IO
    }

    @Provides
    @Singleton
    fun provideNetworkConnectivity(@ApplicationContext context: Context): NetworkConnectivity {
        return Network(context)
    }

    @Provides
    @Singleton
    fun provideUserDatabase(app: Application): VideoDatabase {
        return Room.databaseBuilder(
            app,
            VideoDatabase::class.java, "video"
        ).build();
    }

    @Provides
    @Singleton
    fun provideUserRepository(db: VideoDatabase): VideoRepository {
        return VideoRepositoryImpl(db.VideoDao());
    }
}