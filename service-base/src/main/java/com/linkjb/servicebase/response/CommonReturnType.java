package com.linkjb.servicebase.response;

/**
 * @author sharkshen
 * @data 2019/3/24 23:39
 * @Useage
 */
public class CommonReturnType {
    private String status;
    private Object data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    public static CommonReturnType create(Object result){
        return CommonReturnType.create(result,"success");
    }

    public static CommonReturnType create(Object result,String status){
        CommonReturnType type = new CommonReturnType();
        type.setStatus(status);
        type.setData(result);
        return type;
    }

    @Override
    public String toString() {
        return "CommonReturnType{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}
