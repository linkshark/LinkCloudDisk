package com.linkjb.serviceregist.service;

import com.linkjb.serviceregist.entity.UserLinkMedia;

/**
 * @author sharkshen
 * @data 2019/4/21 16:48
 * @Useage
 */
public interface UserLinkMediaService {
    void Insert(UserLinkMedia media);

    void delete(UserLinkMedia media);

    UserLinkMedia selectByOne(UserLinkMedia link);
}
