package com.linkjb.serviceregist.entity;

/**
 * @author sharkshen
 * @data 2019/4/21 16:40
 * @Useage
 */
public class UserLinkMedia {
    private Integer userId;
    private Integer mediaId;

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

    @Override
    public String toString() {
        return "UserLinkMedia{" +
                "userId=" + userId +
                ", mediaId=" + mediaId +
                '}';
    }
}
