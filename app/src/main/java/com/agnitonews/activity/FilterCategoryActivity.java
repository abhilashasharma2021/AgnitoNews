package com.agnitonews.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.agnitonews.Data.HomeData;
import com.agnitonews.MainActivity;
import com.agnitonews.Model.HomeModel;
import com.agnitonews.R;
import com.agnitonews.Retrofit.APIClient;
import com.agnitonews.adapters.HomeNewsAdapter;
import com.agnitonews.databinding.ActivityFilterCategoryBinding;
import com.agnitonews.databinding.ActivityForgotBinding;
import com.agnitonews.utils.AppConstats;
import com.agnitonews.utils.SharedHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilterCategoryActivity extends AppCompatActivity {
ActivityFilterCategoryBinding binding;
String getCatId="",getCatName="";
    HomeNewsAdapter newsAdapter;
    List<HomeData> homeDataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityFilterCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getCatId = SharedHelper.getKey(getApplicationContext(), AppConstats.CAT_ID);
        getCatName = SharedHelper.getKey(getApplicationContext(), AppConstats.CAT_NAME);
        binding.txTop.setText(getCatName);
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FilterCategoryActivity.this, MainActivity.class));
                overridePendingTransition(R.anim.left_to_right, R.anim.slide_from_right);
                finish();
            }
        });
        binding.rvHome.setLayoutManager(new LinearLayoutManager(FilterCategoryActivity.this, LinearLayoutManager.VERTICAL, false));
        show_Data();
    }

    private void show_Data(){
        Map<String, String> param = new HashMap<>();
        param.put("cid", getCatId);
        Call<HomeModel> call = APIClient.getAPIClient().showNewsByCat(param);
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

                            if (FilterCategoryActivity.this != null) {
                                newsAdapter = new HomeNewsAdapter(FilterCategoryActivity.this , homeDataList);
                                binding.rvHome.setAdapter(newsAdapter);
                            }
                        } else {

                            Toast.makeText(FilterCategoryActivity.this , "SomeThing Went Wrong", Toast.LENGTH_SHORT).show();
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