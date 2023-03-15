package com.agnitonews.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agnitonews.Data.CategoryData;
import com.agnitonews.databinding.RowCategoryBinding;
import com.agnitonews.databinding.RowCategoryHomeBinding;
import com.bumptech.glide.Glide;

import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {


    private Context mContext;
    private List<CategoryData> categoryDataList;

    public CategoryAdapter(Context mContext, List<CategoryData> categoryDataList) {
        this.mContext = mContext;
        this.categoryDataList = categoryDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(RowCategoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CategoryData modelObject = categoryDataList.get(position);

        if (modelObject != null) {
            holder.rowCategoryBinding.txCat.setText(modelObject.getCatName());

            try {
                Glide.with(mContext).load(modelObject.getCatImg()).into(holder.rowCategoryBinding.ivCat);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }

    @Override
    public int getItemCount() {
        return categoryDataList == null ? 0 : categoryDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RowCategoryBinding rowCategoryBinding;

        public MyViewHolder(RowCategoryBinding rowCategoryBinding) {
            super(rowCategoryBinding.getRoot());
            this.rowCategoryBinding = rowCategoryBinding;
        }

    }
}
