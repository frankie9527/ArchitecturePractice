package com.jyh.sixthspace.ui.activity;

import android.os.Bundle;


import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jyh.sixthspace.R;
import com.jyh.sixthspace.databinding.ActivityMainBinding;
import com.jyh.sixthspace.ui.adapter.MainPagerAdapter;
import com.jyh.sixthspace.uilibrary.StatusBarUtil;


public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private BottomNavigationView navigation;
    private MainPagerAdapter mainPagerAdapter;
    private int mAlpha = StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setStatusBar();
        initView();
    }

    private void initView() {
        navigation = binding.navigation;
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        binding.vpMain.setAdapter(mainPagerAdapter);
        binding.vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setStatusBar();
                switch (position) {

                    case 0:
                        navigation.setSelectedItemId(R.id.navigation_recommend);
                        break;
                    case 1:
                        navigation.setSelectedItemId(R.id.navigation_movies);
                        break;
                    case 2:
                        navigation.setSelectedItemId(R.id.navigation_live);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            setStatusBar();
            switch (item.getItemId()) {
                case R.id.navigation_recommend:
                    binding.vpMain.setCurrentItem(0);
                    return true;
                case R.id.navigation_movies:
                    binding.vpMain.setCurrentItem(1);
                    return true;
                case R.id.navigation_live:
                    binding.vpMain.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };


    protected void setStatusBar() {
//        mStatusBarColor = getResources().getColor(R.color.colorPrimary);
//        StatusBarUtil.setColorForDrawerLayout(this, binding.drawerLayout, mStatusBarColor, mAlpha);
        StatusBarUtil.setTranslucentForImageViewInFragment(this, null);
    }
}
