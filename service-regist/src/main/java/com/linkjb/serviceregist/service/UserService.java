package com.linkjb.serviceregist.service;

import com.linkjb.serviceregist.entity.User;

/**
 * @author sharkshen
 * @data 2019/1/17 14:34
 * @Useage
 */
public interface UserService {
    User getUserByUserName(String userName);

    Integer RegistUser(User user);

    User findUserById(String userId);

    void update(User user);

    User getUserByEmailAddress(String emailAddress);
}
