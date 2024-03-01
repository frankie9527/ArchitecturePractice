package com.sixth.space.uitls

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import com.sixth.space.App


/**
 * @author: Frankie
 * @Date: 2024/3/1
 * @Description:
 */
class UIUtils {
    companion object{
        fun getContext(): Context {
            return App.appContext
        }

        // 获取资源文件夹操作
        fun getResources(): Resources {
            return getContext().resources
        }

        // string文件中的字符串
        fun getString(stringId: Int): String? {
            return getResources().getString(stringId)
        }

        // 返回drawable操作
        fun getDrawable(drawableId: Int): Drawable? {
            return getResources().getDrawable(drawableId)
        }

        // string-array
        fun getStringArray(arrayId: Int): Array<String?> {
            return getResources().getStringArray(arrayId)
        }
    }


}