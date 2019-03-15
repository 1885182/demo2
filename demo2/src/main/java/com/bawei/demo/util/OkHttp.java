package com.bawei.demo.util;

import android.os.Handler;
import android.os.Message;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author: zhang
 * @Date: 2019/3/14 14:06
 * @Description:
 */
public class OkHttp {

    OkHttpClient okHttpClient;
    public static OkHttp okHttp;

    //构造方法私有化
    private OkHttp() {
        okHttpClient = new OkHttpClient();
    }
    public static synchronized OkHttp getInstance(){
        if (okHttp == null){
            okHttp = new OkHttp();
        }
        return okHttp;
    }
    public void doGet(String url, final Handler mHandler){
        Request builder = new Request.Builder().get().url(url).build();
        Call call = okHttpClient.newCall(builder);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                Message message = new Message();
                message.obj = json;
                mHandler.sendMessage(message);
            }
        });
    }
}
