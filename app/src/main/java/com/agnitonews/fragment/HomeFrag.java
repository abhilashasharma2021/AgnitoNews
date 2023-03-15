package com.agnitonews.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.agnitonews.Data.CategoryData;
import com.agnitonews.Data.HomeData;
import com.agnitonews.MainActivity;
import com.agnitonews.Model.CategoryModel;
import com.agnitonews.Model.HomeModel;
import com.agnitonews.Retrofit.APIClient;
import com.agnitonews.adapters.CatHomeAdapter;
import com.agnitonews.adapters.CategoryAdapter;
import com.agnitonews.adapters.HomeNewsAdapter;
import com.agnitonews.adapters.TablayoutAdapterDynamic;
import com.agnitonews.databinding.FragmentHomeBinding;
import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFrag extends Fragment {
    public  static FragmentHomeBinding binding;
    View view;
    Context context;
    List<CategoryData> categoryDataList;
    CatHomeAdapter adapter;
    HomeNewsAdapter newsAdapter;
    List<HomeData> homeDataList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(getLayoutInflater(), container, false);
        view = binding.getRoot();
        context = getActivity();



        binding.ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.drawerlayout.openDrawer(GravityCompat.START);
            }
        });


        binding.rvCategory.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        binding.rvHome.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
         show_Category();
          show_Data();
        return view;
    }

    private void show_Category() {
        Call<CategoryModel> call = APIClient.getAPIClient().showCategory();
        call.enqueue(new Callback<CategoryModel>() {
            @Override
            public void onResponse(Call<CategoryModel> call, Response<CategoryModel> response) {

                CategoryModel model = response.body();

                if (model != null) {
                    if (model.getResult().equals("true")) {
                        categoryDataList = new ArrayList<>();
                        List<CategoryModel.Datum> dataList = model.getData();
                        for (CategoryModel.Datum data : dataList) {
                            CategoryData itemData = new CategoryData();
                            for (int i = 0; i < dataList.size(); i++) {
                                itemData.setCatID(data.getId());
                                itemData.setCatName(data.getName());
                                itemData.setCatImg(data.getNameIcon());

                            }
                            categoryDataList.add(itemData);
                        }

                        if (getActivity() != null) {
                            adapter = new CatHomeAdapter(getActivity(), categoryDataList);
                            binding.rvCategory.setAdapter(adapter);
                        }
                    } else {

                        Toast.makeText(getActivity(), "SomeThing Went Wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<CategoryModel> call, Throwable t) {
                Log.e("dfgdgfd", t.getMessage() + "msg");

            }
        });
    }


    private void show_Data(){

        Call<HomeModel> call = APIClient.getAPIClient().showNews();
        call.enqueue(new Callback<HomeModel>() {
            @Override
            public void onResponse(Call<HomeModel> call, Response<HomeModel> response) {
                if (!response.isSuccessful()) {


                } else {
                    HomeModel model = response.body();

                    if (model != null) {
                        if (model.getResult().equals("true")) {
                            homeDataList = new ArrayList<>();
                            List<HomeModel.Datum> dataList = model.getData();
                            for (HomeModel.Datum data : dataList) {
                                HomeData itemData = new HomeData();
                                for (int i = 0; i < dataList.size(); i++) {
                                    itemData.setImg(data.getImageBig());
                                    itemData.setId(data.getId());
                                    itemData.setTittle(data.getTitle());

                                }
                                homeDataList.add(itemData);
                            }

                            if (getActivity() != null) {
                                newsAdapter = new HomeNewsAdapter(getActivity(), homeDataList);
                                binding.rvHome.setAdapter(newsAdapter);
                            }
                        } else {

                            Toast.makeText(getActivity(), "SomeThing Went Wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                }


            }

            @Override
            public void onFailure(Call<HomeModel> call, Throwable t) {
                Log.e("tytytttttttt", t.getMessage() + "msg");

            }
        });



    }

}