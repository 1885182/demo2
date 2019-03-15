package com.bawei.zhangchongru.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei.zhangchongru.MyInterface;
import com.bawei.zhangchongru.bean.Product;
import com.bawei.zhangchongru.presenter.MyPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MyInterface.ViewInterface {

    EditText edit_phone,edit_pwd;
    Button but_login,but_register;
    MyInterface.PresenterInterface presenterInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenterInterface = new MyPresenter<>(this);
        init();
    }

    private void init() {
        edit_phone = findViewById(R.id.edit_phone_id);
        edit_pwd = findViewById(R.id.edit_pwd_id);
        but_login = findViewById(R.id.but_login_id);
        but_register = findViewById(R.id.but_register_id);

        but_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = edit_phone.getText().toString();
                String pwd = edit_pwd.getText().toString();
                presenterInterface.toLogin(phone,pwd);
            }
        });
        but_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = edit_phone.getText().toString();
                String pwd = edit_pwd.getText().toString();
                presenterInterface.toRegister(phone,pwd);
            }
        });
    }


    @Override
    public void showLogin(String str) {
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
        if (str.equals("登录成功")){
            Intent intent = new Intent(this,ViewActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void showRegister(String str) {
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }
}
