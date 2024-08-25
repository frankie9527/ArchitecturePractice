package com.sixth.space

import android.app.Application
import androidx.annotation.CallSuper
import dagger.hilt.android.HiltAndroidApp
import org.various.player.PlayerConfig


@HiltAndroidApp
class App : Application() {
    @CallSuper
    override fun onCreate() {
        super.onCreate()
        PlayerConfig.init(this)
    }
}