package com.linkjb.serviceregist.service.Impl;

import com.linkjb.serviceregist.dao.UserLinkMediaDao;
import com.linkjb.serviceregist.entity.UserLinkMedia;
import com.linkjb.serviceregist.filter.AuthenticationInterceptor;
import com.linkjb.serviceregist.service.UserLinkMediaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

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
    //日志记录
    private static Logger log = LoggerFactory.getLogger(UserLinkMediaServiceImpl.class);
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
    @Async("asyncPromiseExecutor")
    public ListenableFuture<List<Map<String, Object>>> getAllBook(Integer id) {
        log.info(Thread.currentThread().getName());
        return new AsyncResult<>(userLinkMediaDao.getAllBook(id));
    }

}
