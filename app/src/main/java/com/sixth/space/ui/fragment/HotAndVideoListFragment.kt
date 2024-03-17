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
import com.sixth.space.network.error.NETWORK_EMPTY
import com.sixth.space.network.error.NO_INTERNET_CONNECTION
import com.sixth.space.ui.adapter.HotAndVideoAdapter
import com.sixth.space.ui.activity.VideoDetailsActivity
import com.sixth.space.uitls.observe
import dagger.hilt.android.AndroidEntryPoint
import org.easy.ui.recycler.listener.ItemClickListener
import org.various.player.utils.LogUtils

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
        when (position) {
            Constant.fragment_type_recommend -> {
                observe(viewModel.recipesRecommendData, ::handleRecipesRecommendData)
            }
            Constant.fragment_type_comment -> {
                observe(viewModel.recipesReplyData, ::handleRecipesCommentData)
            }
            else -> {
                observe(viewModel.recipesHotData, ::handleRecipesData)
            }
        }



        binding.recycler.layoutManager = LinearLayoutManager(context);
        adapter = HotAndVideoAdapter()
        adapter.setItemListener(this);
        binding.recycler.adapter = adapter;
        extracted()
        binding.controlLayout.setOnRetryListener {
            extracted()
        }

    }

    private fun extracted() {
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

    fun refresh(info: VideoInfo) {
        when (position) {
            3 -> {
                LogUtils.d("jyh", "recommend fetch");
                viewModel.fetchRecommend(info.videoId.toString())
            }

            4 -> {
                LogUtils.d("jyh", "comment fetch");
                viewModel.fetchReplyComment(info.videoId.toString())
            }
        }
        videoInfo = info;
    }
    fun handleRecipesRecommendData(status: Resource<List<VideoInfo>>) {
        LogUtils.d("jyh", " handleRecipesRecommendData"+" position="+position);
        handleRecipesData(status)
    }
    fun handleRecipesCommentData(status: Resource<List<VideoInfo>>) {
        LogUtils.d("jyh", " handleRecipesCommentData"+" position="+position);
        handleRecipesData(status)
    }

    fun handleRecipesData(status: Resource<List<VideoInfo>>) {
        when (status) {
            is Resource.Loading -> binding.controlLayout.showLoading()
            is Resource.Success -> status.data?.let {
                binding.controlLayout.hideLoading()
                adapter.clear()
                val data = status.data;
                videoInfo?.let { it1 ->
                    if (it1.videoType == Constant.recycler_adapter_type_recommend_head && position == Constant.fragment_type_recommend) {
                        adapter.insertDataInHead(it1)
                    }
                }
                adapter.setData(data);
            }

            is Resource.DataError -> {
                if (status.errorCode == NO_INTERNET_CONNECTION) {
                    binding.controlLayout.showNoNet();
                    return
                }
                if (status.errorCode == NETWORK_EMPTY) {
                    binding.controlLayout.showEmpty();
                    return
                }
                binding.controlLayout.showError();
            }
        }
    }

    override fun onItemClick(view: View?, position: Int) {
        val adapterType = adapter.getItemViewType(position);
        if (adapterType == Constant.recycler_adapter_type_hot || adapterType == Constant.recycler_adapter_type_recommend) {
            val intent = Intent(activity, VideoDetailsActivity::class.java)
            val data = adapter.getDataInPostion(position);
            data.videoType = Constant.recycler_adapter_type_recommend_head;
            intent.putExtra(Constant.VIDEO_INFO, data);
            startActivity(intent)
        }

    }
}





