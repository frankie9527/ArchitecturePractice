package com.sixth.space

import android.app.Application
import android.content.Context
import androidx.annotation.CallSuper
import dagger.hilt.android.HiltAndroidApp


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
    }
}