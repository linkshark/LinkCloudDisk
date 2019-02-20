package com.linkjb.servicebase.dao;

import com.linkjb.servicebase.pojo.NameData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author sharkshen
 * @data 2019/2/19 14:22
 * @Useage
 */
@Mapper
public interface TestDao {
     void insertIntoOcl(NameData data);
}
