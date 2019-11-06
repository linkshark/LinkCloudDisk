package com.linkjb.serviceregist.dao;

import com.linkjb.servicepojo.pojo.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * @author sharkshen
 * @data 2019/1/17 14:30
 * @Useage
 */
@Mapper
public interface UserDao extends BaseMapper<User> {
    User getUserByUserName(String userName);

    Integer RegistUser(User user);

    List<User> getAllUser();

    @Select(value = "select * from user where id = #{userId}")
    User findUserById(@Param("userId") String userId);

    void updateUser(User user);

    User getUserByEmailAddress(String emailAddress);
}
