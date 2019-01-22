package com.linkjb.serviceregist.base;
import java.util.List;

/**
 * @author sharkshen
 * @data 2019/1/16 16:22
 * @Useage 返回结果类
 *  *
 *  * 包含一个List<T>
 *  * 状态status
 *  * 某个类对象 T
 *  *
 *  */

public class BaseResult<T> {
    private String status;       //处理结果状态
    private String message;      //返回信息
    private T entity;            //返回实体对象
    private List<T> list;        //返回结果集

    public BaseResult(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public BaseResult(String status, String message, T entity, List<T> list) {
        this.status = status;
        this.message = message;
        this.entity = entity;
        this.list = list;
    }

    public BaseResult(String status, T entity, List<T> list) {
        this.list = list;
        this.status = status;
        this.entity = entity;
    }

    public BaseResult( String status,List<T> list) {
        this.list = list;
        this.status = status;
    }

    public BaseResult(String status, T entity) {
        this.status = status;
        this.entity = entity;
    }

    public BaseResult() {
    }

    public BaseResult(String status) {
        this.status = status;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        switch (this.status = status) {
        }
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", entity=" + entity +
                ", list=" + list +
                '}';
    }
}
