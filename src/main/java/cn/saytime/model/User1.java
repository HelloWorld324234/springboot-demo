package cn.saytime.model;

import cn.saytime.framework.core.pojo.model.GenericGuidModel;

public class User1 extends GenericGuidModel {


    private String name;

    private String address;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
