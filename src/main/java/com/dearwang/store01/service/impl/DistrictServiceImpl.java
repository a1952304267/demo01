package com.dearwang.store01.service.impl;

import com.dearwang.store01.entity.District;
import com.dearwang.store01.mapper.DistrictMapper;
import com.dearwang.store01.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements IDistrictService {
    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public List<District> getByParent(String parent) {
        List<District> districts = districtMapper.findByParentId(parent);
        /**
         * 未减少无效数据传递，将无效数据设置为null
         */
        for (District d : districts) {
            d.setId(null);
            d.setParent(null);
        }
        return districts;
    }

    @Override
    public String getNameByCode(String code) {
        return districtMapper.findNameByCode(code);
    }
}
