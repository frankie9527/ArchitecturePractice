package com.sixth.space.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.sixth.space.databinding.ActivityMainBinding
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sixth.space.model.MainViewModel
import com.sixth.space.R

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                !viewModel.go2main.value
            }
        }
        initView();

    }
    private fun initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.viewPager2.adapter = MainFragmentStateAdapter(this);
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
