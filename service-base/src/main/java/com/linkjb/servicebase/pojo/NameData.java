package com.linkjb.servicebase.pojo;

/**
 * @author sharkshen
 * @data 2019/2/19 10:20
 * @Useage
 */
/**
 * 名字+身份证号数据实体类
 * @class_name NameData.java
 */
public class NameData {

    private String name;

    private String idCardNumber;

    private String mobile = "13606601596";


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    @Override
    public String toString() {
        return "NameData{" +
                "name='" + name + '\'' +
                ", idCardNumber='" + idCardNumber + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}