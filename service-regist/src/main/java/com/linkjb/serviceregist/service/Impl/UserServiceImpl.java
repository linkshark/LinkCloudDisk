package com.linkjb.serviceregist.service.Impl;

import com.linkjb.serviceregist.dao.UserDao;
import com.linkjb.serviceregist.entity.User;
import com.linkjb.serviceregist.service.UserService;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author sharkshen
 * @data 2019/1/17 14:35
 * @Useage
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public User getUserByUserName(String userName) {
        return userDao.getUserByUserName(userName);
    }

    @Override
    public Integer RegistUser(User user) {
        return userDao.RegistUser(user);
    }

    @Override
    public User findUserById(String userId) {
        return userDao.findUserById(userId);
    }
}
