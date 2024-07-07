package com.dearwang.store01.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

//老规矩使用lombok自动生成，另外继承基类
//收货地址的数据实体类
//@AllArgsConstructor不如@Data
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
public class Address extends BaseEntity implements Serializable {
    private Integer aid;
    private Integer uid;
    private String tname;
    private String provinceName;
    private String provinceCode;
    private String cityName;
    private String cityCode;
    private String areaName;
    private String areaCode;
    private String zip;
    private String address;
    private String phone;
    private String tel;
    private String tag;
    private Integer isDefault;
    private Integer isDelete;
}
