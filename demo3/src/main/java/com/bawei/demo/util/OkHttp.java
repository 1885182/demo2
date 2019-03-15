package com.bawei.demo.util;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @Author: zhang
 * @Date: 2019/3/14 19:16
 * @Description:
 */
public class OkHttp {
    static OkHttp okHttp;

    private OkHttp() {
    }

    public static synchronized OkHttp getInstance(){
        if (okHttp == null){
            okHttp = new OkHttp();
        }
        return okHttp;
    }
    public void doPost(String phone, String pwd, Callback callback){
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        //添加参数,键值对,键需要对应接口文档中键
        builder.add("phone",phone);
        builder.add("pwd",pwd);
        //构建你请求参数的类RequestBody
        RequestBody body = builder.build();
        //构建请求的配置Request
        Request request = new Request.Builder()
                .post(body)
                .url("http://172.17.8.100/small/user/v1/register")
                .build();
        //具体的实际执行类 call
        Call call = okHttpClient.newCall(request);
        //call 通过队列执行请求
        call.enqueue(callback);
    }
    public void dPost(String phone, String pwd, Callback callback){
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        //添加参数,键值对,键需要对应接口文档中键
        builder.add("phone",phone);
        builder.add("pwd",pwd);
        //构建你请求参数的类RequestBody
        RequestBody body = builder.build();
        //构建请求的配置Request
        Request request = new Request.Builder().post(body).url("http://172.17.8.100/small/user/v1/login").build();
        //具体的实际执行类 call
        Call call = okHttpClient.newCall(request);
        //call 通过队列执行请求
        call.enqueue(callback);
    }
}
