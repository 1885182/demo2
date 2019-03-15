package com.bawei.zhangchongru.util;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @Author: zhang
 * @Date: 2019/3/15 8:54
 * @Description:
 */
public class OkHttpUtil {
    OkHttpClient okHttpClient;
    static OkHttpUtil okHttpUtil;

    private OkHttpUtil() {
        okHttpClient = new OkHttpClient();
    }
    public static synchronized OkHttpUtil getInstance(){
        if (okHttpUtil == null){
            okHttpUtil = new OkHttpUtil();
        }
        return okHttpUtil;
    }
    public void doGet(String url, final Handler mHandler, final int type){
        Request request = new Request.Builder().get().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Message message = new Message();
                message.obj = json;
                message.arg1 = type;
                mHandler.sendMessage(message);
            }
        });
    }
    public void doRegisterPost(String phone,String pwd,Callback callback){
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("phone",phone);
        builder.add("pwd",pwd);
        Request request = new Request.Builder()
                .url("http://172.17.8.100/small/user/v1/register")
                .post(builder.build())
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }
    public void doLoginPost(String phone,String pwd,Callback callback){
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("phone",phone);
        builder.add("pwd",pwd);
        Request request = new Request.Builder()
                .url("http://172.17.8.100/small/user/v1/login")
                .post(builder.build())
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }
}
