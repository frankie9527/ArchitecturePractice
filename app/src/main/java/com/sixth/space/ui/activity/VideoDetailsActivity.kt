package com.sixth.space.ui.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sixth.space.base.BaseActivity

import com.sixth.space.databinding.ActivityVideoDetailsBinding
import com.sixth.space.ui.fragment.HotListFragment
import com.sixth.space.ui.fragment.VideoDetailsCommentFragment
import com.sixth.space.ui.fragment.VideoDetailsRecommendFragment
import dagger.hilt.android.AndroidEntryPoint
import org.various.player.PlayerConstants
import org.various.player.listener.UserActionListener

/**
 * @author: Frankie
 * @Date: 2024/3/5
 * @Description:
 */
@AndroidEntryPoint
class VideoDetailsActivity : BaseActivity() {
//    lateinit var videoData: HotList.Item;
    var url = "https://d1.xia12345.com/video/202310/6524242a37926f1bd8c3740c/hd.mp4"
    val binding by lazy {
        ActivityVideoDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        videoData = if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU){
//            intent.getSerializableExtra(Constant.VIDEO_ITEM,HotList.Item::class.java) as HotList.Item
//        }else{
//            intent.getSerializableExtra(Constant.VIDEO_ITEM) as HotList.Item
//        }


    }
    override fun initViewBinding() {
        binding.simpleView.setPlayData(url, "title")
        binding.simpleView.startSyncPlay()
        binding.simpleView.setUserActionListener(UserActionListener { action -> if (action == PlayerConstants.ACTION_BACK) finish() })
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("简介"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("评论"))
        binding.viewPager.adapter= VideoDetailsFragmentStateAdapter(this);
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
class VideoDetailsFragmentStateAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity){
    override fun getItemCount(): Int {
        return 2;
    }

    override fun createFragment(position: Int): Fragment {
        return if (position==0){
             HotListFragment().newInstance(3);
//            VideoDetailsRecommendFragment()
        }else {
            HotListFragment().newInstance(4);
//            VideoDetailsCommentFragment();
        }


    }

}