package com.sixth.space.ui.fragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2

import com.sixth.space.base.Constant
import com.sixth.space.data.dao.VideoInfo
import com.sixth.space.databinding.FragmentTiktokBinding
import com.sixth.space.model.RemoteViewModel
import com.sixth.space.network.Resource
import com.sixth.space.network.error.NO_INTERNET_CONNECTION
import com.sixth.space.ui.adapter.TikTokAdapter
import com.sixth.space.uitls.LogUtils
import com.sixth.space.uitls.observe
import dagger.hilt.android.AndroidEntryPoint

import org.easy.ui.recycler.listener.ItemClickListener

@AndroidEntryPoint
class TiktokFragment : Fragment(), ItemClickListener {
    lateinit var binding: FragmentTiktokBinding;
    lateinit var adapter: TikTokAdapter;
    var position: Int = 0;
    val viewModel: RemoteViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTiktokBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun newInstance(position: Int): TiktokFragment {
        val args = Bundle()
        val fragment = TiktokFragment()
        args.putInt(Constant.HOT_POSITION, position)
        fragment.arguments = args
        return fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView();

    }

    fun initView() {
        val position = requireArguments().getInt(Constant.HOT_POSITION)
        viewModel.fetchTiktokData(position)
        observe(viewModel.recipesTiktokData, ::handleRecipesData)
        adapter = TikTokAdapter();
        binding.recycler.adapter = adapter;
        binding.recycler.registerOnPageChangeCallback(TikTokOnPageChange());

    }

    fun handleRecipesData(status: Resource<List<VideoInfo>>) {
        when (status) {
            is Resource.Loading -> binding.controlLayout.showLoading()
            is Resource.Success -> status.data?.let {
                binding.controlLayout.hideLoading()
                val data = status.data;
                adapter.setData(data);
            }

            is Resource.DataError -> {
                if (status.errorCode == NO_INTERNET_CONNECTION) {
                    binding.controlLayout.showNoNet();
                    return
                }
                binding.controlLayout.showError();
            }
        }
    }

    override fun onItemClick(view: View?, position: Int) {

    }


}

class TikTokOnPageChange : ViewPager2.OnPageChangeCallback() {
    override fun onPageSelected(position: Int) {
        super.onPageSelected(position)

    }
}





