package com.linkjb.serviceregist.dao;

import com.linkjb.servicepojo.pojo.user.UserLinkMedia;
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

    UserLinkMedia selectByUserIdAndMediaId(@Param("id") Integer id,@Param("mediaId") String mediaId);
}
