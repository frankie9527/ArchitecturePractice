package com.sixth.space

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

/**
 * @author: Frankie
 * @Date: 2024/4/7
 * @Description: testInstrumentationRunner = "com.sixth.space.HiltJUnitRunner" 在设置版本号中设置
 */
class HiltJUnitRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}