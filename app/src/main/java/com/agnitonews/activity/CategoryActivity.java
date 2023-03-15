package com.agnitonews.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.agnitonews.Data.CategoryData;
import com.agnitonews.MainActivity;
import com.agnitonews.Model.CategoryModel;
import com.agnitonews.R;
import com.agnitonews.Retrofit.APIClient;
import com.agnitonews.adapters.CatHomeAdapter;
import com.agnitonews.adapters.CategoryAdapter;
import com.agnitonews.databinding.ActivityCategoryBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CategoryActivity extends AppCompatActivity {
ActivityCategoryBinding binding;
    List<CategoryData> categoryDataList;
    CategoryAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CategoryActivity.this, MainActivity.class));
                finish();
            }
        });
        layoutManager = new LinearLayoutManager(CategoryActivity.this, LinearLayoutManager.VERTICAL, true);
        binding.rvCategory.setLayoutManager(new GridLayoutManager(CategoryActivity.this, 2));

        show_Category();
    }


    private void show_Category() {
        Call<CategoryModel> call = APIClient.getAPIClient().showCategory();
        call.enqueue(new Callback<CategoryModel>() {
            @Override
            public void onResponse(Call<CategoryModel> call, Response<CategoryModel> response) {
                if (!response.isSuccessful()) {


                } else {
                    CategoryModel model = response.body();

                    if (model != null) {
                        if (model.getResult().equals("true")) {
                            categoryDataList = new ArrayList<>();
                            List<CategoryModel.Datum> dataList = model.getData();
                            for (CategoryModel.Datum data : dataList) {
                                CategoryData itemData = new CategoryData();
                                for (int i = 0; i < dataList.size(); i++) {
                                    itemData.setCatName(data.getName());
                                    itemData.setCatImg(data.getNameIcon());

                                }
                                categoryDataList.add(itemData);
                            }

                            if (CategoryActivity.this != null) {
                                adapter = new CategoryAdapter(CategoryActivity.this, categoryDataList);
                                binding.rvCategory.setAdapter(adapter);
                            }
                        } else {

                            Toast.makeText(CategoryActivity.this, "SomeThing Went Wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                }


            }

            @Override
            public void onFailure(Call<CategoryModel> call, Throwable t) {
                Log.e("eryruiyd", t.getMessage() + "msg");

            }
        });
    }

}