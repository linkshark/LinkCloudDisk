package com.linkjb.servicews.dao;


import com.linkjb.servicews.entity.Media;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MediaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Media record);

    int insertSelective(Media record);

    Media selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Media record);

    int updateByPrimaryKey(Media record);

    int getCountByMediaName(@Param(value = "mediaName") String mediaName);

    List<Media> getMediaByText(@Param(value = "mediaName")String mediaName);
}