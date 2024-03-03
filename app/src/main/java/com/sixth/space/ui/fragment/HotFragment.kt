package com.sixth.space.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.sixth.space.R
import com.sixth.space.databinding.FragmentHotBinding

class HotFragment : Fragment() {
    lateinit var binding: FragmentHotBinding;
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHotBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val hotList: Array<String?> = resources.getStringArray(R.array.hot_list);
        for (str in hotList) {
            binding.tabLayout.addTab(binding.tabLayout.newTab().setText(str))
        }
        binding.viewPager.adapter= HotListFragmentStateAdapter(this);
        binding.tabLayout.addOnTabSelectedListener(object :OnTabSelectedListener{
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
        binding.viewPager.registerOnPageChangeCallback(object :OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }

        })
    }
}
class HotListFragmentStateAdapter(fm: Fragment) :
    FragmentStateAdapter(fm){
    override fun getItemCount(): Int {
        return 3;
    }

    override fun createFragment(position: Int): HotListFragment {
        return HotListFragment().newInstance(position);
    }

}