package com.sixth.space

import android.app.Application
import android.content.Context
import androidx.annotation.CallSuper
import dagger.hilt.android.HiltAndroidApp
import org.easy.tools.EasySdk
import org.various.player.PlayerConfig


@HiltAndroidApp
class App : Application() {
    companion object {
        lateinit var appContext: Context
            private set
    }

    @CallSuper
    override fun onCreate() {
        super.onCreate()
        appContext = this
        EasySdk.init(this);
        PlayerConfig.init(this)
    }
}