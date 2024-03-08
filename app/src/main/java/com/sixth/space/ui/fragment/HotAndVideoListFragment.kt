package com.sixth.space.ui.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sixth.space.base.Constant
import com.sixth.space.data.dao.VideoInfo
import com.sixth.space.databinding.FragmentRecyclerBinding

import com.sixth.space.model.RemoteViewModel
import com.sixth.space.network.Resource
import com.sixth.space.network.error.NO_INTERNET_CONNECTION
import com.sixth.space.ui.adapter.HotAndVideoAdapter
import com.sixth.space.ui.activity.VideoDetailsActivity
import com.sixth.space.uitls.observe
import dagger.hilt.android.AndroidEntryPoint
import org.easy.ui.recycler.listener.ItemClickListener

@AndroidEntryPoint
class HotAndVideoListFragment : Fragment(), ItemClickListener {
    lateinit var binding: FragmentRecyclerBinding;
    val viewModel: RemoteViewModel by viewModels()
    lateinit var adapter: HotAndVideoAdapter;
    var position: Int = 0;
    var videoId: Int = 0;
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecyclerBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun newInstance(position: Int, videoId: Int): HotAndVideoListFragment {
        val args = Bundle()
        val fragment = HotAndVideoListFragment()
        args.putInt(Constant.HOT_POSITION, position)
        args.putInt(Constant.VIDEO_ID, videoId)
        fragment.arguments = args
        return fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewAndData();

    }


    fun initViewAndData() {
        position = requireArguments().getInt(Constant.HOT_POSITION)
        videoId = requireArguments().getInt(Constant.VIDEO_ID)
        observe(viewModel.recipesHotData, ::handleRecipesData)
        observe(viewModel.recipesReplyData, ::handleRecipesData)
        observe(viewModel.recipesRecommendData, ::handleRecipesData)
        binding.recycler.layoutManager = LinearLayoutManager(context);
        adapter = HotAndVideoAdapter()
        adapter.setItemListener(this);
        binding.recycler.adapter = adapter;
        when (position) {
            0, 1, 2 -> {
                viewModel.fetchHotData(position)
            }

            3 -> {
                viewModel.fetchRecommend(videoId.toString())
            }

            4 -> {
                viewModel.fetchReplyComment(videoId.toString())
            }
        }

    }


    fun  handleRecipesData(status: Resource<List<VideoInfo>>) {
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
        val adapterType = adapter.getItemViewType(position);
        if (adapterType == Constant.recycler_adapter_type_hot) {
            val intent = Intent(activity, VideoDetailsActivity::class.java)
            intent.putExtra(Constant.VIDEO_ITEM, adapter.getDataInPostion(position));
            startActivity(intent)
        }

    }
}





