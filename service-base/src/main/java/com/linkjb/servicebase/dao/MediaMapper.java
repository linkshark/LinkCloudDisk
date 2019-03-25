package com.linkjb.servicebase.dao;

import com.linkjb.servicebase.pojo.Media;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MediaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Media record);

    int insertSelective(Media record);

    Media selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Media record);

    int updateByPrimaryKey(Media record);

    int getCountByMediaName(@Param(value = "mediaName") String mediaName);
}