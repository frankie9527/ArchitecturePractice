package com.sixth.space.base

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ImmersionBar
import com.sixth.space.R

/**
 * @author: Frankie
 * @Date: 2024/3/5
 * @Description:
 */
abstract class BaseActivity : AppCompatActivity() {
    abstract fun observeViewModel()
    protected abstract fun initViewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        initStatusBar();
        initViewBinding()
        observeViewModel()
    }

    fun initStatusBar() {
       ImmersionBar.with(this).statusBarColor(R.color.white).statusBarDarkFont(true).init()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}