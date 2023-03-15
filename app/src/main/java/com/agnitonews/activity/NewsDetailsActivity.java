package com.agnitonews.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import com.agnitonews.MainActivity;
import com.agnitonews.Model.NewsDetailsModel;
import com.agnitonews.Retrofit.APIClient;
import com.agnitonews.databinding.ActivityNewsDetailsBinding;
import com.agnitonews.utils.AppConstats;
import com.agnitonews.utils.SharedHelper;
import com.bumptech.glide.Glide;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsDetailsActivity extends AppCompatActivity {
ActivityNewsDetailsBinding binding;
String stPostId="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityNewsDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Log.e("clkdlkc", "onCreate: "+stPostId );
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });


    }



}