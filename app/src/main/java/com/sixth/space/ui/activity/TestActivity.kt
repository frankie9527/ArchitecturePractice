package com.sixth.space.ui.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.sixth.space.R
import com.sixth.space.base.BaseResp
import com.sixth.space.data.HotItem
import com.sixth.space.network.Resource

import com.sixth.space.model.TestViewModel

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestActivity : AppCompatActivity() {
    val viewModel: TestViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)


    }

    fun hotList(view: View) {
        viewModel.demo();
    }

    fun monthly(view: View) {
        val  intent= Intent(this,VideoDetailsActivity::class.java)
        startActivity(intent)
    }

    fun historical(view: View) {

    }
    private fun handleRecipesList(status: Resource<BaseResp<HotItem>>) {
        when (status) {
            is Resource.Loading -> hello("Loading")
            is Resource.Success -> status.data?.let { hello("Success") }
            is Resource.DataError -> {
                hello("DataError")
            }
        }
    }
    fun hello(str: String) {
        Log.e("jyh", "str=" + str)
    }
    var REQUEST_STORAGE_PERMISSION = 0
    private fun checkReadPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            //TODO 此处写第一次检查权限且已经拥有权限后的业务
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf<String>(Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_STORAGE_PERMISSION
            )
        }
    }

}
