package com.bawei.demo.v;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bawei.demo.R;
import com.bawei.demo.adapter.MyAdapter;
import com.bawei.demo.bean.Product;
import com.bawei.demo.p.MyPresenter;
import com.bawei.demo.p.PresenterInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhang
 * @Date: 2019/3/14 14:04
 * @Description:
 */
public class ViewActivity extends Activity implements ViewInterface{
    RecyclerView recyclerView;
    List<Product> list = new ArrayList<>();
    MyAdapter adapter;
    PresenterInterface presenterInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyAdapter(list,this);
        recyclerView.setAdapter(adapter);
        presenterInterface = new MyPresenter(this);
        presenterInterface.toModel();
    }


    @Override
    public void reFreDisplay(List<Product> vList) {
        list.addAll(vList);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenterInterface.onDestroy();
        presenterInterface = null;
    }
}
