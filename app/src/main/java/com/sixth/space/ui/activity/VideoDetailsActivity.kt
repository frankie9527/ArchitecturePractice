package com.sixth.space.ui.activity

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.sixth.space.R
import com.sixth.space.base.BaseActivity
import com.sixth.space.base.Constant
import com.sixth.space.data.dao.VideoInfo

import com.sixth.space.databinding.ActivityVideoDetailsBinding
import com.sixth.space.ui.fragment.HotAndVideoListFragment
import dagger.hilt.android.AndroidEntryPoint
import org.various.player.PlayerConstants
import org.various.player.listener.UserActionListener

/**
 * @author: Frankie
 * @Date: 2024/3/5
 * @Description:
 * 322603 dongjing
 */
@AndroidEntryPoint
class VideoDetailsActivity : BaseActivity() {
    //note that :
    private val info: VideoInfo by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(Constant.VIDEO_ITEM, VideoInfo::class.java) as VideoInfo
        } else {
            intent.getSerializableExtra(Constant.VIDEO_ITEM) as VideoInfo
        }
    };
    val binding by lazy {
        ActivityVideoDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }

    override fun initViewBinding() {
        binding.simpleView.setPlayData(info.playUrl, info.title)
        Glide.with(this).load(info.blurred).into(binding.imgBackGround);
        binding.simpleView.startSyncPlay()

        binding.simpleView.setUserActionListener(UserActionListener { action -> if (action == PlayerConstants.ACTION_BACK) finish() })
        binding.tabLayout.addTab(
            binding.tabLayout.newTab().setText(resources.getString(R.string.introduction))
        )
        binding.tabLayout.addTab(
            binding.tabLayout.newTab().setText(resources.getString(R.string.comment))
        )
       binding.viewPager.adapter = VideoDetailsFragmentStateAdapter(this,info.videoId);
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    binding.viewPager.currentItem = tab.position
                };
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    binding.viewPager.currentItem = tab.position
                }
            }
        })
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }

        })
    }

    override fun observeViewModel() {

    }


    override fun onPause() {
        super.onPause()
        binding.simpleView.pause()
    }

    override fun onResume() {
        super.onResume()
        binding.simpleView.resume()
    }


    override fun onDestroy() {
        super.onDestroy()
        binding.simpleView.release()
    }
}

class VideoDetailsFragmentStateAdapter(fragmentActivity: FragmentActivity,private val videoId:Int) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2;
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            HotAndVideoListFragment().newInstance(Constant.fragment_type_recommend,videoId);
        } else {
            HotAndVideoListFragment().newInstance(Constant.fragment_type_comment,videoId);

        }
    }

}