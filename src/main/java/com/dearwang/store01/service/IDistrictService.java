package com.dearwang.store01.service;

import com.dearwang.store01.entity.District;

import java.util.List;

public interface IDistrictService {
    /**
     * 根据父代号返回查询区域信息
     *
     * @param parent 父代号
     * @return 多个区域信息的结果集
     */
    List<District> getByParent(String parent);

    String getNameByCode(String code);
}
