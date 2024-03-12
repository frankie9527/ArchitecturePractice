package com.sixth.space.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.sixth.space.R
import com.sixth.space.base.BaseActivity
import com.sixth.space.base.Constant
import com.sixth.space.data.dao.VideoInfo
import com.sixth.space.databinding.ActivitySearchBinding
import com.sixth.space.model.SearchViewModel
import com.sixth.space.network.Resource
import com.sixth.space.network.error.NO_INTERNET_CONNECTION
import com.sixth.space.ui.adapter.SearchAdapter
import com.sixth.space.uitls.observe
import dagger.hilt.android.AndroidEntryPoint
import org.easy.ui.recycler.listener.ItemClickListener

/**
 * @author: Frankie
 * @Date: 2024/3/8
 * @Description:
 */
@AndroidEntryPoint
class SearchActivity : BaseActivity() , ItemClickListener {
    val viewModel: SearchViewModel by viewModels()
    lateinit var adapter: SearchAdapter;
    val binding by lazy {
        ActivitySearchBinding.inflate(layoutInflater)
    }

    override fun observeViewModel() {
        observe(viewModel.recipesData, ::handleRecipes)
    }

    override fun initViewBinding() {
        binding.toolbar.inflateMenu(R.menu.search_menu)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
        binding.toolbar.setOnMenuItemClickListener {
            val data = binding.edSearch.text.toString();
            viewModel.search(data)
            return@setOnMenuItemClickListener true;
        }
        adapter = SearchAdapter()
        adapter.setItemListener(this)
        binding.included.recycler.layoutManager = LinearLayoutManager(this);
        binding.included.recycler.adapter = adapter;

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    private fun handleRecipes(status: Resource<List<VideoInfo>>) {
        when (status) {
            is Resource.Loading -> binding.included.controlLayout.showLoading()
            is Resource.Success -> status.data?.let {
                binding.included.controlLayout.hideLoading();
                if (status.data.size == 0) {
                    binding.included.controlLayout.showEmpty();
                    return
                }
                 Glide.with(this).load(R.mipmap.blurry).into(binding.imgBackGround);
                adapter.clear()
                adapter.setData(status.data)
            }

            is Resource.DataError -> {
                if (status.errorCode == NO_INTERNET_CONNECTION) {
                    binding.included.controlLayout.showNoNet();
                    return
                }
                binding.included.controlLayout.showError();

            }
        }
    }

    override fun onItemClick(view: View?, position: Int) {
        val intent = Intent(this, VideoDetailsActivity::class.java)
        intent.putExtra(Constant.VIDEO_INFO, adapter.getDataInPostion(position));
        startActivity(intent)
    }
}