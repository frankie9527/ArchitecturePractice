package com.sixth.space.ui.activity

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.sixth.space.databinding.ActivityMainBinding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sixth.space.model.MainViewModel
import com.sixth.space.R
import com.sixth.space.ui.fragment.HomeFragment
import com.sixth.space.ui.fragment.HotFragment
import com.sixth.space.ui.onMenuClickListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), onMenuClickListener {
    private val viewModel by viewModels<MainViewModel>()
    private var menu: View? = null
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                !viewModel.go2main.value
            }
        }
        initViewBinding()
    }

    fun initViewBinding() {
        setContentView(binding.root)
        binding.slideLayout.setSliderFadeColor(0)
        menu = findViewById(R.id.menu)
        val params: ViewGroup.LayoutParams = menu!!.getLayoutParams()
        params.width = (resources.displayMetrics.widthPixels * 0.85f).toInt()
        menu!!.setLayoutParams(params)
        binding.viewPager2.adapter = MainFragmentStateAdapter(this, this);
        binding.viewPager2.isUserInputEnabled = false;
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    onNavigationBackGround(0)
                    binding.viewPager2.setCurrentItem(0, false);
                }

                R.id.navigation_hot -> {
                    onNavigationBackGround(1)
                    binding.viewPager2.setCurrentItem(1, false);
                }
            }
            true
        }
        onNavigationBackGround(0)
    }

    override fun onMenuClick() {
        if (!binding.slideLayout.isOpen) {
            binding.slideLayout.openPane()
        }
    }


    fun onNavigationBackGround(position: Int) {
        var textColor: Int? = null;
        var background: Int? = null;
        if (position == 0) {
            textColor=  R.color.main_tab_tiktok_color_selector
            background = Color.BLACK
        } else {
            textColor= R.color.main_tab_color_selector
            background = Color.WHITE
        }
        val cls: ColorStateList = resources.getColorStateList(textColor, null)
        binding.bottomNavigationView.itemTextColor = cls;
        binding.bottomNavigationView.itemIconTintList=cls
        binding.bottomNavigationView.setBackgroundColor(background)
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
