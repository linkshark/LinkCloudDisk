package com.linkjb.servicews.service.impl;

import com.linkjb.servicews.dao.LinkMediaMapper;
import com.linkjb.servicews.dao.MediaMapper;
import com.linkjb.servicews.entity.LinkMedia;
import com.linkjb.servicews.entity.Media;
import com.linkjb.servicews.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName MovieServiceImpl
 * @Description TODO
 * @Author shark
 * @Data 2020/2/22 15:18
 **/
@Slf4j
@Service
public class MovieServiceImpl implements MovieService {
    @Resource
    MediaMapper mediaMapper;
    @Resource
    LinkMediaMapper linkMediaMapper;
    @Override
    public List<Media> getMediaByText(String text) {
        return mediaMapper.getMediaByText(text);
    }

    @Override
    public List<LinkMedia> getLinkMediaByLinkId(String linkId) {
        return linkMediaMapper.getLinkMediaByLinkId(linkId);
    }
}
