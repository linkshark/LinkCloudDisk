package com.linkjb.serviceregist.dao;

import com.linkjb.serviceregist.entity.UserLinkMedia;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * @author sharkshen
 * @data 2019/4/21 16:42
 * @Useage
 */
@Mapper
public interface UserLinkMediaDao extends BaseMapper<UserLinkMedia> {
    List<Map<String, Object>> getAllBook(@Param("id") Integer id);
}
