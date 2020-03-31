package com.linkjb.servicepojo.goods;

import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

public class GoodsCategory implements Serializable {
    private static final long serialVersionUID = 6977402643848374753L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String categoryName;

    private String gmt_create;

    private String gmt_update;

    private String parentCategoryId;


}
