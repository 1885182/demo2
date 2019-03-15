package com.bawei.demo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bawei.demo.R;
import com.bawei.demo.bean.Product;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * @Author: zhang
 * @Date: 2019/3/14 14:48
 * @Description:
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.viewHolder> {

    List<Product> aList;
    Context context;

    public MyAdapter(List<Product> aList, Context context) {
        this.aList = aList;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout,null);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {
        viewHolder.textView.setText(aList.get(i).getCommodityName());
        Glide.with(context).load(aList.get(i).getMasterPic()).into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        if (aList != null){
            return aList.size();
        }
        return 0;
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
