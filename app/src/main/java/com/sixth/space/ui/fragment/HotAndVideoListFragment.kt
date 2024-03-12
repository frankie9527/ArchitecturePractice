package com.sixth.space.ui.fragment


import android.content.Intent
import android.os.Build
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
    var videoInfo: VideoInfo? = null;
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecyclerBinding.inflate(inflater, container, false)
        return binding.root
    }

    fun newInstance(position: Int, videoInFo: VideoInfo?): HotAndVideoListFragment {
        val args = Bundle()
        val fragment = HotAndVideoListFragment()
        args.putInt(Constant.HOT_POSITION, position)
        args.putSerializable(Constant.VIDEO_INFO, videoInFo)
        fragment.arguments = args
        return fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewAndData();

    }


    fun initViewAndData() {
        arguments?.let {
            position = it.getInt(Constant.HOT_POSITION)
            if (position == Constant.fragment_type_recommend || position == Constant.fragment_type_comment) {
                videoInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    it.getSerializable(Constant.VIDEO_INFO, VideoInfo::class.java) as VideoInfo
                } else {
                    it.getSerializable(Constant.VIDEO_INFO) as VideoInfo
                }
            }

        }
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
                viewModel.fetchRecommend(videoInfo!!.videoId.toString())
            }

            4 -> {
                viewModel.fetchReplyComment(videoInfo!!.videoId.toString())
            }
        }

    }


    fun handleRecipesData(status: Resource<List<VideoInfo>>) {
        when (status) {
            is Resource.Loading -> binding.controlLayout.showLoading()
            is Resource.Success -> status.data?.let {
                binding.controlLayout.hideLoading()
                val data = status.data;
                if (position==Constant.fragment_type_recommend){
                    videoInfo?.let { it1 -> adapter.insertDataInHead(it1) }
                }
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
            intent.putExtra(Constant.VIDEO_INFO, adapter.getDataInPostion(position));
            startActivity(intent)
        }

    }
}





