package com.sixth.space.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.sixth.space.databinding.ActivityMainBinding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sixth.space.model.MainViewModel
import com.sixth.space.R
import com.sixth.space.ui.fragment.DiscoveryFragment
import com.sixth.space.ui.fragment.HomeFragment
import com.sixth.space.ui.fragment.HotFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        installSplashScreen().apply {
//            setKeepOnScreenCondition {
//                !viewModel.go2main.value
//            }
//        }
        initView();

    }

    private fun initView() {
        setContentView(binding.root)
        binding.viewPager2.adapter = MainFragmentStateAdapter(this);
        binding.viewPager2.isUserInputEnabled = false;
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    binding.viewPager2.setCurrentItem(0, true);
                }

                R.id.navigation_discovery -> {
                    binding.viewPager2.setCurrentItem(1, true);
                }

                R.id.navigation_hot -> {
                    binding.viewPager2.setCurrentItem(2, true);
                }
            }
            true
        }
    }
}

class MainFragmentStateAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = 3
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> DiscoveryFragment()
            else -> HotFragment();
        }
    }

}
