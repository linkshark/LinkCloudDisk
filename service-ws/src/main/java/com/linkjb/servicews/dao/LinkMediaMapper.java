package com.linkjb.servicews.dao;

import com.linkjb.servicews.entity.LinkMedia;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LinkMediaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LinkMedia record);

    int insertSelective(LinkMedia record);

    LinkMedia selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LinkMedia record);

    int updateByPrimaryKey(LinkMedia record);

    List<LinkMedia> getLinkMediaByLinkId(@Param("linkId") String linkId);
}