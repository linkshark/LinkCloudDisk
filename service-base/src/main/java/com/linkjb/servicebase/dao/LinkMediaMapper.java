package com.linkjb.servicebase.dao;

import com.linkjb.servicebase.pojo.LinkMedia;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LinkMediaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LinkMedia record);

    int insertSelective(LinkMedia record);

    LinkMedia selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LinkMedia record);

    int updateByPrimaryKey(LinkMedia record);
}