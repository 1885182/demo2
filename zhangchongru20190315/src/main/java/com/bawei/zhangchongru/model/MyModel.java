package com.bawei.zhangchongru.model;

import android.os.Handler;
import android.os.Message;

import com.bawei.zhangchongru.MyInterface;
import com.bawei.zhangchongru.bean.ProductBean;
import com.bawei.zhangchongru.util.OkHttpUtil;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 * @Author: zhang
 * @Date: 2019/3/15 8:48
 * @Description:
 */
public class MyModel implements MyInterface.ModelInterface {
    MyCallBack mCallBack;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String json = (String) msg.obj;
            int type = msg.arg1;
            switch (type){
                case 1:
                    Gson gson = new Gson();
                    ProductBean bean = gson.fromJson(json,ProductBean.class);
                    mCallBack.success(bean);
                    break;
                case 2:
                    String message = (String) msg.obj;
                    mCallBack.success(message);
                    break;
                case 3:
                    String messag = (String) msg.obj;
                    mCallBack.success(messag);
                    break;
            }
        }
    };
    @Override
    public void toRequest(String url, int type, MyCallBack myCallBack) {
        this.mCallBack = myCallBack;
        OkHttpUtil.getInstance().doGet(url,handler,type);
    }

    @Override
    public void LoginRequest(String phone, String pwd,MyCallBack yCallBack) {
        mCallBack = yCallBack;
        OkHttpUtil.getInstance().doLoginPost(phone, pwd, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                try {
                    JSONObject object = new JSONObject(json);
                    String mes = object.getString("message");
                    Message message = new Message();
                    message.obj = mes;
                    message.arg1 = 3;
                    handler.sendMessage(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void RegisterRequest(String phone, String pwd, MyCallBack yCallBack) {
        mCallBack = yCallBack;
        OkHttpUtil.getInstance().doRegisterPost(phone, pwd, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String mes = jsonObject.getString("message");
                    Message message = new Message();
                    message.obj = mes;
                    message.arg1 = 2;
                    handler.sendMessage(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public interface MyCallBack{
        public void success(Object str);
    }
}
