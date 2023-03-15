package com.agnitonews.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.agnitonews.Data.CategoryData;
import com.agnitonews.MainActivity;
import com.agnitonews.R;
import com.agnitonews.activity.FilterCategoryActivity;
import com.agnitonews.databinding.RowCategoryHomeBinding;
import com.agnitonews.utils.AppConstats;
import com.agnitonews.utils.SharedHelper;
import com.bumptech.glide.Glide;

import java.util.List;


public class CatHomeAdapter extends RecyclerView.Adapter<CatHomeAdapter.MyViewHolder> {


    private Context mContext;
    private List<CategoryData> categoryDataList;

    public CatHomeAdapter(Context mContext, List<CategoryData> categoryDataList) {
        this.mContext = mContext;
        this.categoryDataList = categoryDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowCategoryHomeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CategoryData modelObject = categoryDataList.get(position);

        if (modelObject != null) {
            holder.rowCategoryHomeBinding.txName.setText(modelObject.getCatName());

            try {
                Glide.with(mContext).load(modelObject.getCatImg()).into(holder.rowCategoryHomeBinding.ivNew);
            } catch (Exception e) {
                e.printStackTrace();
            }


            holder.rowCategoryHomeBinding.rlCat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    SharedHelper.putKey(mContext, AppConstats.CAT_ID, modelObject.getCatID());
                    SharedHelper.putKey(mContext, AppConstats.CAT_NAME, modelObject.getCatName());
                    mContext.startActivity(new Intent(mContext, FilterCategoryActivity.class));
                    ((Activity) mContext).overridePendingTransition(R.anim.left_to_right,R.anim.slide_from_right);



                }
            });

        }


    }

    @Override
    public int getItemCount() {
        return categoryDataList == null ? 0 : categoryDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowCategoryHomeBinding rowCategoryHomeBinding;

        public MyViewHolder(RowCategoryHomeBinding rowCategoryHomeBinding) {
            super(rowCategoryHomeBinding.getRoot());
            this.rowCategoryHomeBinding = rowCategoryHomeBinding;
        }

    }
}
