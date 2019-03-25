package com.linkjb.servicebase.error;

/**
 * @author sharkshen
 * @data 2019/3/24 23:21
 * @Useage
 */
public interface CommonError {
    public int getErrCode();
    public String getErrMsg();
    public CommonError setErrMsg(String errMsg);


}
