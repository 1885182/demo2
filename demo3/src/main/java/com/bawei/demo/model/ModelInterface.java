package com.bawei.demo.model;

/**
 * @Author: zhang
 * @Date: 2019/3/14 18:49
 * @Description:
 */
public interface ModelInterface {
    public void LoginRequest(String phone, String pwd, MyModel.MyCallBack myCallBack);
    public void RegisterRequest(String phone,String pwd,MyModel.MyCallBack myCallBack);
}
