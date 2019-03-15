package com.bawei.demo.bean;

/**
 * @Author: zhang
 * @Date: 2019/3/13 16:41
 * @Description:
 */
public class Product {
    private String commodityName;
    private String masterPic;

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getMasterPic() {
        return masterPic;
    }

    public void setMasterPic(String masterPic) {
        this.masterPic = masterPic;
    }

    @Override
    public String toString() {
        return "Product{" +
                "commodityName='" + commodityName + '\'' +
                ", masterPic='" + masterPic + '\'' +
                '}';
    }
}
