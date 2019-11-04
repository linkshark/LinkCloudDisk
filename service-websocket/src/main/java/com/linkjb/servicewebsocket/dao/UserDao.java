package com.linkjb.servicewebsocket.dao;

import com.linkjb.servicewebsocket.entity.User;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;
@Mapper
public interface UserDao extends BaseMapper<User> {
}
