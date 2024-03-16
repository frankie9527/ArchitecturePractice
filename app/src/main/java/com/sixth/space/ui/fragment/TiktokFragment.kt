package com.sixth.space.ui.fragment


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.StrictMode
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.sixth.space.R

import com.sixth.space.base.Constant
import com.sixth.space.data.dao.VideoInfo
import com.sixth.space.databinding.FragmentTiktokBinding
import com.sixth.space.model.RemoteViewModel
import com.sixth.space.network.Resource
import com.sixth.space.network.error.NETWORK_EMPTY
import com.sixth.space.network.error.NO_INTERNET_CONNECTION
import com.sixth.space.ui.adapter.TikTokAdapter
import com.sixth.space.uitls.observe
import dagger.hilt.android.AndroidEntryPoint

import org.easy.ui.recycler.listener.ItemClickListener
import org.various.player.core.VariousPlayerManager
import java.io.File

@AndroidEntryPoint
class TiktokFragment : Fragment(), ItemClickListener {
    lateinit var binding: FragmentTiktokBinding;
    lateinit var adapter: TikTokAdapter;
    var type: Int? = null;
    var currentPlayPosition = -1;
    val viewModel: RemoteViewModel by viewModels()
    lateinit var commentDialog: TiktokCommentFragment;

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
        args.putInt(Constant.TIKTOK_TYPE, position)
        fragment.arguments = args
        return fragment
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView();

    }

    fun initView() {
        //share apk
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        builder.detectFileUriExposure()

        type = requireArguments().getInt(Constant.TIKTOK_TYPE)
        viewModel.fetchTiktokData(type!!)
        observe(viewModel.recipesTiktokData, ::handleRecipesData)
        adapter = TikTokAdapter();
        binding.recycler.adapter = adapter;
        adapter.setItemListener(this)
        binding.recycler.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                playTikTokItem(position)
            }
        });
        adapter.setRecycler(binding.recycler.getChildAt(0) as RecyclerView)
        commentDialog = TiktokCommentFragment();
        commentDialog.setTikTokViewModel(viewModel)
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
                if (status.errorCode == NETWORK_EMPTY) {
                    binding.controlLayout.showEmpty();
                    return
                }
                binding.controlLayout.showError();
            }
        }
    }

    override fun onItemClick(view: View, position: Int) {
        if (view.id == R.id.image_share) {
            val path = requireActivity().applicationContext.packageResourcePath;
            val apkFile = File(path)
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.type = "*/*"
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(apkFile))
            activity?.startActivity(intent)
        } else if (view.id == R.id.image_comment) {
            val data=adapter.getDataInPostion(position);
            commentDialog.show(childFragmentManager, "commentDialog")
            commentDialog.fetchComment(data.videoId.toString())
        }
    }

    fun playTikTokItem(position: Int) {
        if (position == currentPlayPosition) {
            return
        }
        val videoView = adapter.getVideoView(position);
        val data = adapter.getDataInPostion(position);
        videoView!!.setDataAndPlay(data.playUrl)
        viewModel.fetchReplyComment(data.videoId.toString())
        currentPlayPosition = position;
    }

    override fun onPause() {
        super.onPause()
        VariousPlayerManager.pause()
    }

    override fun onResume() {
        super.onResume()
        if (currentPlayPosition == -1) {
            return
        }
        val videoView = adapter.getVideoView(currentPlayPosition);
        val data = adapter.getDataInPostion(currentPlayPosition);
        viewModel.fetchReplyComment(data.videoId.toString())
        videoView!!.setDataAndPlay(data.playUrl)
    }
}







