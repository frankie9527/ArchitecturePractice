package com.sixth.space.ui.fragment


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sixth.space.base.BaseResp
import com.sixth.space.base.Constant
import com.sixth.space.data.HotItem
import com.sixth.space.data.ReplyItem
import com.sixth.space.databinding.FragmentRecyclerBinding

import com.sixth.space.model.RemoteViewModel
import com.sixth.space.network.Resource
import com.sixth.space.network.error.NO_INTERNET_CONNECTION
import com.sixth.space.ui.HotListAdapter
import com.sixth.space.ui.activity.VideoDetailsActivity
import com.sixth.space.uitls.observe
import dagger.hilt.android.AndroidEntryPoint
import org.easy.ui.recycler.listener.ItemClickListener
import java.util.Objects

@AndroidEntryPoint
class HotListFragment : Fragment(), ItemClickListener {
    lateinit var binding: FragmentRecyclerBinding;
    val viewModel: RemoteViewModel by viewModels()
    lateinit var adapter: HotListAdapter;
    var position: Int = 0;
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecyclerBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun newInstance(position: Int): HotListFragment {
        val args = Bundle()
        val fragment = HotListFragment()
        args.putInt(Constant.HOT_POSITION, position)
        fragment.arguments = args
        return fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewAndData();

    }


    fun initViewAndData() {
        position = requireArguments().getInt(Constant.HOT_POSITION)
        observe(viewModel.recipesHotData, ::handleRecipesHotList)
        observe(viewModel.recipesReplyData, ::handleRecipesReplyHotList)
        binding.recycler.layoutManager = LinearLayoutManager(context);
        adapter = HotListAdapter(position)
        adapter.setItemListener(this);
        binding.recycler.adapter = adapter;
        when (position) {
            0, 1, 2 -> {
                viewModel.fetchHotData(position)
            }

            3 -> {
                viewModel.videoRecommend()
            }

            4 -> {
                viewModel.fetchReplyComment("322130")
            }
        }

    }

    private fun handleRecipesHotList(status: Resource<BaseResp<HotItem>>) {
        dealData(status)
    }

    private fun handleRecipesReplyHotList(status: Resource<BaseResp<ReplyItem>>) {
        dealData(status)
    }

    fun <T> dealData(status: Resource<BaseResp<T>>) {
        when (status) {
            is Resource.Loading -> binding.controlLayout.showLoading()
            is Resource.Success -> status.data?.let {
                binding.controlLayout.hideLoading()
                val data = status.data.itemList as List<Objects>;
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

    override fun onItemClick(p0: View?, position: Int) {
        val intent = Intent(activity, VideoDetailsActivity::class.java)
        startActivity(intent)
    }


}





