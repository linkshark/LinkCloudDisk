package com.linkjb.serviceregist.service.Impl;

import com.linkjb.serviceregist.dao.UserLinkMediaDao;
import com.linkjb.serviceregist.entity.UserLinkMedia;
import com.linkjb.serviceregist.filter.AuthenticationInterceptor;
import com.linkjb.serviceregist.service.UserLinkMediaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.Scheduled;
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
//  @Cacheable(value = "emp" ,key = "targetClass +methodName +#p0") TODO 未实现serizable接口 无法使用cacheable注解
    public ListenableFuture<List<Map<String, Object>>> getAllBook(Integer id) {
        //log.info(Thread.currentThread().getName());
        return new AsyncResult<>(userLinkMediaDao.getAllBook(id));
    }
    /*
     *功能描述
     * 每隔10S清楚emp缓存空间中的缓存,延时实时性
     * @author shark
     * @date 2019/4/28
     * @param  * @param
     * @return void
     */
    @Override
    @CacheEvict(value = "emp",allEntries = true)
    //@Scheduled(fixedDelay = 10000)
    public void CleanCache() {
    log.info("--清除缓存!!");
    }

    @Override
    public Boolean uniqueCheck(Integer id, String mediaId) {

        UserLinkMedia userLinkMedia = userLinkMediaDao.selectByUserIdAndMediaId(id,mediaId);
        if(userLinkMedia==null){
            return true;
        }else{
            return false;
        }
    }

}
