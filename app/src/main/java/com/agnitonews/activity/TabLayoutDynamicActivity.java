package com.agnitonews.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.agnitonews.R;
import com.agnitonews.adapters.TablayoutAdapterDynamic;
import com.agnitonews.databinding.ActivityCategoryBinding;
import com.agnitonews.databinding.ActivityTabLayoutDynamicBinding;
import com.google.android.material.tabs.TabLayout;

public class TabLayoutDynamicActivity extends AppCompatActivity {
    ActivityTabLayoutDynamicBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityTabLayoutDynamicBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initViews();
    }
    private void initViews() {

        // initialise the layout


        // setOffscreenPageLimit means number
        // of tabs to be shown in one page
        binding.viewPager.setOffscreenPageLimit(5);
        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener( binding.tabLayout));
        binding.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // setCurrentItem as the tab position
                binding.viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        setDynamicFragmentToTabLayout();
    }

    // show all the tab using DynamicFragmnetAdapter
    private void setDynamicFragmentToTabLayout() {
        // here we have given 10 as the tab number
        // you can give any number here
        for (int i = 0; i < 10; i++) {
            // set the tab name as "Page: " + i
            binding.tabLayout.addTab( binding.tabLayout.newTab().setText("Page: " + i));
        }
        TablayoutAdapterDynamic mDynamicFragmentAdapter = new TablayoutAdapterDynamic(getSupportFragmentManager(),  binding.tabLayout.getTabCount());

        // set the adapter
        binding.viewPager.setAdapter(mDynamicFragmentAdapter);

        // set the current item as 0 (when app opens for first time)
        binding.viewPager.setCurrentItem(0);
    }
}
