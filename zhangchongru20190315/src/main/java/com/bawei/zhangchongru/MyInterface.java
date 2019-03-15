package com.bawei.zhangchongru;

import com.bawei.zhangchongru.bean.Product;
import com.bawei.zhangchongru.model.MyModel;

import java.util.List;

/**
 * @Author: zhang
 * @Date: 2019/3/15 11:26
 * @Description:
 */
public interface MyInterface {
    //model层的接口
    public interface ModelInterface {
        /**
         *
         * @param url:接口路径
         * @param type:接口标志
         * @param myCallBack:数据返回的接口,用来把model层的数据返回到p层
         */
        public void toRequest(String url, int type, MyModel.MyCallBack myCallBack);
        public void LoginRequest(String phone, String pwd, MyModel.MyCallBack yCallBack);
        public void RegisterRequest(String phone, String pwd, MyModel.MyCallBack yCallBack);
    }
    //presenter层的接口
    public interface PresenterInterface {
        public void toModel();
        public void toLogin(String phone, String pwd);
        public void toRegister(String phone, String pwd);
    }
    //view层的接口
    public interface ViewInterface {
        //去p层接收数据集合
        public interface reFe{
            public void reFreDisplay(List<Product> vList);
        };
        public void showLogin(String str);
        public void showRegister(String str);
    }
}
