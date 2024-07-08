package com.dearwang.store01.mapper;

import com.dearwang.store01.entity.District;

import java.util.List;

public interface DistrictMapper {
    /**
     * 根据父代号查询区域信息
     * @param parentId 父代号
     * @return 返回父区域下的所有区域列表
     */
    List<District> findByParentId(String parentId);

    String findNameByCode(String code);
}
