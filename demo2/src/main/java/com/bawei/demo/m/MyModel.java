package com.bawei.demo.m;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.bawei.demo.bean.ProductBean;
import com.bawei.demo.util.OkHttp;
import com.google.gson.Gson;

/**
 * @Author: zhang
 * @Date: 2019/3/14 13:56
 * @Description:
 */
public class MyModel implements ModelInterface {
    MyCallBack mCallBack;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String json = (String) msg.obj;
            Gson gson = new Gson();
            ProductBean bean = gson.fromJson(json,ProductBean.class);
            mCallBack.success(bean);
        }
    };
    @Override
    public void toRequest(String url, MyCallBack myCallBack) {
        mCallBack = myCallBack;
        OkHttp.getInstance().doGet(url,handler);
    }

    public interface MyCallBack{
        public void success(Object obj);
        public void error(String str);
    }
}
