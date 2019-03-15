package com.bawei.demo.presenter;

/**
 * @Author: zhang
 * @Date: 2019/3/14 18:49
 * @Description:
 */
public interface PresenterInterface {
    //去登陆
    public void toLogin(String phone,String pwd);
    //去注册
    public void toRegister(String phone,String pwd);
}
