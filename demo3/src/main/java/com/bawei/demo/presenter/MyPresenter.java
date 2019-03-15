package com.bawei.demo.presenter;

import com.bawei.demo.model.ModelInterface;
import com.bawei.demo.model.MyModel;
import com.bawei.demo.view.ViewInterface;

import java.io.PipedWriter;

/**
 * @Author: zhang
 * @Date: 2019/3/14 18:49
 * @Description:
 */
public class MyPresenter implements PresenterInterface{
    ModelInterface modelInterface;
    ViewInterface viewInterface;

    public MyPresenter(ViewInterface viewInterface) {
        this.viewInterface = viewInterface;
        modelInterface = new MyModel();
    }

    @Override
    public void toLogin(String phone, String pwd) {
        modelInterface.LoginRequest(phone, pwd, new MyModel.MyCallBack() {
            @Override
            public void success(String mes) {
                viewInterface.showLogin(mes);
            }
        });
    }

    @Override
    public void toRegister(String phone, String pwd) {
        modelInterface.RegisterRequest(phone, pwd, new MyModel.MyCallBack() {
            @Override
            public void success(String mes) {
                viewInterface.showRegister(mes);
            }
        });
    }
}
