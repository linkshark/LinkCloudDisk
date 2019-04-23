package com.linkjb.serviceregist.service.Impl;

import com.linkjb.serviceregist.dao.UserDao;
import com.linkjb.serviceregist.dao.UserLinkMediaDao;
import com.linkjb.serviceregist.entity.UserLinkMedia;
import com.linkjb.serviceregist.service.UserLinkMediaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author sharkshen
 * @data 2019/4/21 16:50
 * @Useage
 */
@Service
public class UserLinkMediaServiceImpl implements UserLinkMediaService {
    @Resource
    private UserLinkMediaDao userLinkMediaDao;
    @Override
    public void Insert(UserLinkMedia media) {
        userLinkMediaDao.insert(media);
    }

    @Override
    public void delete(UserLinkMedia media) {
        userLinkMediaDao.delete(media);
    }

    @Override
    public UserLinkMedia selectByOne(UserLinkMedia link) {
        return userLinkMediaDao.selectOne(link);
    }

    @Override
    public List<Map<String, Object>> getAllBook(Integer id) {
        return userLinkMediaDao.getAllBook(id);
    }

}
