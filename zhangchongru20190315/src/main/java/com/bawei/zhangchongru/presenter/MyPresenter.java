package com.bawei.zhangchongru.presenter;

import android.util.Log;

import com.bawei.zhangchongru.MyInterface;
import com.bawei.zhangchongru.bean.Product;
import com.bawei.zhangchongru.bean.ProductBean;
import com.bawei.zhangchongru.model.MyModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhang
 * @Date: 2019/3/15 8:48
 * @Description:
 */
public class MyPresenter<T> implements MyInterface.PresenterInterface {
    MyInterface.ModelInterface modelInterface;
    MyInterface.ViewInterface.reFe viewInterface;
    MyInterface.ViewInterface lViewInterface;
    T tt;
    public MyPresenter(T t) {
        modelInterface = new MyModel();
        tt = t;
    }

    @Override
    public void toModel() {
        modelInterface.toRequest("http://172.17.8.100/small/commodity/v1/commodityList", 1, new MyModel.MyCallBack() {
            @Override
            public void success(Object str) {
                viewInterface = (MyInterface.ViewInterface.reFe) tt;
                if (str instanceof ProductBean){
                    ProductBean bean = (ProductBean) str;
                    viewInterface.reFreDisplay(bean.getResult().getMlss().getCommodityList());
                    viewInterface.reFreDisplay(bean.getResult().getPzsh().getCommodityList());
                    viewInterface.reFreDisplay(bean.getResult().getRxxp().getCommodityList());
                }
            }
        });
    }

    @Override
    public void toLogin(String phone, String pwd) {
        lViewInterface = (MyInterface.ViewInterface) tt;
        modelInterface.LoginRequest(phone,pwd, new MyModel.MyCallBack() {
            @Override
            public void success(Object str) {
                String string = (String) str;
                lViewInterface.showLogin(string);
            }
        });
    }

    @Override
    public void toRegister(String phone, String pwd) {
        lViewInterface = (MyInterface.ViewInterface) tt;
        modelInterface.RegisterRequest(phone, pwd, new MyModel.MyCallBack() {
            @Override
            public void success(Object str) {
                String string = (String) str;
                lViewInterface.showRegister(string);
            }
        });
    }
}
