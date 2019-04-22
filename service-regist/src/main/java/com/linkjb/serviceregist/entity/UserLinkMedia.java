package com.linkjb.serviceregist.entity;

import java.util.Date;

/**
 * @author sharkshen
 * @data 2019/4/21 16:40
 * @Useage
 */
public class UserLinkMedia {
    private Integer userId;
    private Integer mediaId;
    private Date subScribeTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getMediaId() {
        return mediaId;
    }

    public void setMediaId(Integer mediaId) {
        this.mediaId = mediaId;
    }

    public Date getSubScribeTime() {
        return subScribeTime;
    }

    public void setSubScribeTime(Date subScribeTime) {
        this.subScribeTime = subScribeTime;
    }

    @Override
    public String toString() {
        return "UserLinkMedia{" +
                "userId=" + userId +
                ", mediaId=" + mediaId +
                ", subScribeTime=" + subScribeTime +
                '}';
    }
}
