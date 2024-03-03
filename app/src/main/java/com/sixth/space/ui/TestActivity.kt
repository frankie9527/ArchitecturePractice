package com.sixth.space.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.sixth.space.R
import com.sixth.space.data.HotList
import com.sixth.space.data.Resource

import com.sixth.space.model.TestViewModel
import com.sixth.space.ui.fragment.hello
import com.sixth.space.uitls.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestActivity : AppCompatActivity() {
    val viewModel: TestViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
//        observe(viewModel.recipesLiveData, ::handleRecipesList)

    }

    fun hotList(view: View) {
        viewModel.demo();
    }

    fun monthly(view: View) {
        checkReadPermission()
    }

    fun historical(view: View) {

    }
    private fun handleRecipesList(status: Resource<HotList>) {
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
