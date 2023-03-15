package com.agnitonews.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agnitonews.Data.CategoryData;
import com.agnitonews.Data.HomeData;
import com.agnitonews.activity.NewsDetailsActivity;
import com.agnitonews.databinding.RowCategoryHomeBinding;
import com.agnitonews.databinding.RowhomelayoutBinding;
import com.agnitonews.utils.AppConstats;
import com.agnitonews.utils.SharedHelper;
import com.bumptech.glide.Glide;

import java.util.List;


public class HomeNewsAdapter extends RecyclerView.Adapter<HomeNewsAdapter.MyViewHolder> {


    private Context mContext;
    private List<HomeData> homeDataList;

    public HomeNewsAdapter(Context mContext, List<HomeData> homeDataList) {
        this.mContext = mContext;
        this.homeDataList = homeDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowhomelayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        HomeData modelObject = homeDataList.get(position);

        if (modelObject != null) {
            holder.rowhomelayoutBinding.txTittle.setText(modelObject.getTittle());

            try {
                Glide.with(mContext).load(modelObject.getImg()).into(holder.rowhomelayoutBinding.ivNews);
            } catch (Exception e) {
                e.printStackTrace();
            }

            holder.rowhomelayoutBinding.rlDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    SharedHelper.putKey(mContext, AppConstats.POST_ID,modelObject.getId());
                   mContext.startActivity(new Intent(mContext, NewsDetailsActivity.class));

                }
            });

            holder.rowhomelayoutBinding.ivShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "http://codepath.com");
                    mContext.startActivity(Intent.createChooser(shareIntent, "Share link using"));

                }
            });

        }


    }

    @Override
    public int getItemCount() {
        return homeDataList == null ? 0 : homeDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowhomelayoutBinding rowhomelayoutBinding;

        public MyViewHolder(RowhomelayoutBinding rowhomelayoutBinding) {
            super(rowhomelayoutBinding.getRoot());
            this.rowhomelayoutBinding = rowhomelayoutBinding;
        }

    }
}
