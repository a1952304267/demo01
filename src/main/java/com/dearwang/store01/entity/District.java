package com.dearwang.store01.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;


//从数据库获取省市区的数据实体类
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
public class District extends BaseEntity implements Serializable {
    private Integer id;
    private String parent;
    private String name;
    private String code;
}
