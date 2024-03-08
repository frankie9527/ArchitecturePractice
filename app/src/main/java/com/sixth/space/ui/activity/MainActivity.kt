package com.sixth.space.ui.activity

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import com.sixth.space.databinding.ActivityMainBinding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sixth.space.model.MainViewModel
import com.sixth.space.R
import com.sixth.space.base.BaseActivity
import com.sixth.space.ui.fragment.HomeFragment
import com.sixth.space.ui.fragment.HotFragment
import com.sixth.space.ui.onMenuClickListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity(), onMenuClickListener {
    private val viewModel by viewModels<MainViewModel>()
    private var menu: View? = null
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun observeViewModel() {

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        installSplashScreen().apply {
//            setKeepOnScreenCondition {
//                !viewModel.go2main.value
//            }
//        }

    }

    override fun initViewBinding() {
        setContentView(binding.root)
        binding.slideLayout.setSliderFadeColor(0)
        menu = findViewById(R.id.menu)
        val params: ViewGroup.LayoutParams = menu!!.getLayoutParams()
        params.width = (resources.displayMetrics.widthPixels * 0.85f).toInt()
        menu!!.setLayoutParams(params)

        binding.viewPager2.adapter = MainFragmentStateAdapter(this,this);
        binding.viewPager2.isUserInputEnabled = false;
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    binding.viewPager2.setCurrentItem(0, false);
                }
                R.id.navigation_hot -> {
                    binding.viewPager2.setCurrentItem(1, false);
                }
            }
            true
        }
    }

    override fun onMenuClick() {
        if (!binding.slideLayout.isOpen) {
            binding.slideLayout.openPane()
        }
    }
}

class MainFragmentStateAdapter(
    fragmentActivity: FragmentActivity,
    private val listener: onMenuClickListener
) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 2
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment(listener)
            else -> HotFragment(listener);
        }
    }

}
