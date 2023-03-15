package com.agnitonews.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.agnitonews.R;


public class DynamicallyFragment extends Fragment {

    View view;
    int val;
    TextView c;
    ImageView ivCat;
    public static DynamicallyFragment newInstance() {
        return new DynamicallyFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view= inflater.inflate(R.layout.fragment_dynamically, container, false);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        TextView textView = view.findViewById(R.id.commonTextView);
        textView.setText("Category :  " + getArguments().getInt("position"));
    }
    @Override
    public void onPause() {
        super.onPause();
    }

    // resume function call
    @Override
    public void onResume() {
        super.onResume();
    }

    // stop when we close
    @Override
    public void onStop() {
        super.onStop();
    }

    // destroy the view
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}