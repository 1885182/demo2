package com.bawei.demo.model;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.bawei.demo.util.OkHttp;
import com.bawei.demo.view.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @Author: zhang
 * @Date: 2019/3/14 18:48
 * @Description:
 */
public class MyModel implements ModelInterface{
    MyCallBack mCallBack;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //1是注册返回的数据   2是登录返回的数据
            int type = msg.arg1;
            if (type == 1){
                String message = (String) msg.obj;
                mCallBack.success(message);
            }else if (type == 2){
                String message = (String) msg.obj;
                mCallBack.success(message);
            }
        }
    };
    @Override
    public void LoginRequest(String phone, String pwd,MyCallBack myCallBack) {
        this.mCallBack = myCallBack;
        OkHttp.getInstance().dPost(phone, pwd, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                try {
                    JSONObject object = new JSONObject(json);
                   // JSONObject result = object.getJSONObject("result");
                   // String headPic = result.getString("headPic");
                    String mes = object.getString("message");
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

    @Override
    public void RegisterRequest(String phone, String pwd, MyCallBack myCallBack) {
        this.mCallBack = myCallBack;
        OkHttp.getInstance().doPost(phone, pwd, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //拿到返回的json数据
                String json = response.body().string();
                try {
                    //手动解析json
                    JSONObject object = new JSONObject(json);
                    String mes = object.getString("message");
                    //创建消息类
                    Message message = new Message();
                    message.obj = mes;
                    message.arg1 = 1;
                    handler.sendMessage(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //创建接口用来返回数据到p层
    public interface MyCallBack{
        public void success(String mes);
    }
}
