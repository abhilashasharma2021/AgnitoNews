package com.agnitonews.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agnitonews.R;
import com.agnitonews.adapters.TablayoutAdapterDynamic;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;


public class DataFragment extends Fragment {
    ViewPager viewPager;
    TabLayout tabLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_data, container, false);
        viewPager=view.findViewById(R.id.viewPager);
        tabLayout=view.findViewById(R.id.tabLayout);
        initViews();
        return view;
    }

    private void initViews() {

        // initialise the layout


        // setOffscreenPageLimit means number
        // of tabs to be shown in one page
       viewPager.setOffscreenPageLimit(5);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
     tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // setCurrentItem as the tab position
                viewPager.setCurrentItem(tab.getPosition());
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
            tabLayout.addTab( tabLayout.newTab().setText("Page: " + i));
        }
        TablayoutAdapterDynamic mDynamicFragmentAdapter = new TablayoutAdapterDynamic(requireActivity().getSupportFragmentManager(),tabLayout.getTabCount());

        // set the adapter
     viewPager.setAdapter(mDynamicFragmentAdapter);

        // set the current item as 0 (when app opens for first time)
     viewPager.setCurrentItem(0);
    }
}