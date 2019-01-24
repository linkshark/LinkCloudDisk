package com.linkjb.serviceregist.dao;

import com.linkjb.serviceregist.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author sharkshen
 * @data 2019/1/17 14:30
 * @Useage
 */
@Mapper
public interface UserDao {
    User getUserByUserName(String userName);

    Integer RegistUser(User user);

    List<User> getAllUser();

    @Select(value = "select * from user where id = #{userId}")
    User findUserById(@Param("userId") String userId);
}
