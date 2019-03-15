package com.bawei.zhangchongru.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.bawei.zhangchongru.MyInterface;
import com.bawei.zhangchongru.adapter.MyAdapter;
import com.bawei.zhangchongru.bean.Product;
import com.bawei.zhangchongru.presenter.MyPresenter;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity implements MyInterface.ViewInterface.reFe {
    RecyclerView recyclerView;
    List<Product> list = new ArrayList<>();
    MyInterface.PresenterInterface presenterInterface;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
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
}
