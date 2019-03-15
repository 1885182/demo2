package com.bawei.demo.p;

import com.bawei.demo.bean.ProductBean;
import com.bawei.demo.m.ModelInterface;
import com.bawei.demo.m.MyModel;
import com.bawei.demo.v.ViewInterface;

/**
 * @Author: zhang
 * @Date: 2019/3/14 14:05
 * @Description:
 */
public class MyPresenter implements PresenterInterface{
    ModelInterface modelInterface;
    ViewInterface viewInterface;

    public MyPresenter(ViewInterface viewInterface) {
        modelInterface = new MyModel();
        this.viewInterface = viewInterface;
    }

    @Override
    public void toModel() {
        modelInterface.toRequest("http://172.17.8.100/small/commodity/v1/commodityList", new MyModel.MyCallBack() {
            @Override
            public void success(Object obj) {
                if (obj instanceof ProductBean){
                    ProductBean bean = (ProductBean) obj;
                    viewInterface.reFreDisplay(bean.getResult().getRxxp().getCommodityList());
                    viewInterface.reFreDisplay(bean.getResult().getPzsh().getCommodityList());
                    viewInterface.reFreDisplay(bean.getResult().getMlss().getCommodityList());
                }
            }

            @Override
            public void error(String str) {

            }
        });
    }

    @Override
    public void onDestroy() {
        viewInterface = null;
    }
}
