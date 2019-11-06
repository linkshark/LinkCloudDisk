package com.linkjb.serviceregist.service;

import com.linkjb.servicepojo.pojo.user.UserLinkMedia;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.Map;

/**
 * @author sharkshen
 * @data 2019/4/21 16:48
 * @Useage
 */
public interface UserLinkMediaService {

    void Insert(UserLinkMedia media);

    void delete(UserLinkMedia media);

    UserLinkMedia selectByOne(UserLinkMedia link);

    ListenableFuture<List<Map<String, Object>>> getAllBook(Integer id);

    void CleanCache();

    Boolean uniqueCheck(Integer id, String mediaId);
}
