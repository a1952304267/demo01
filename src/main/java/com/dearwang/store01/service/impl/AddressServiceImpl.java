package com.dearwang.store01.service.impl;


import com.dearwang.store01.entity.Address;
import com.dearwang.store01.entity.District;
import com.dearwang.store01.mapper.AddressMapper;
import com.dearwang.store01.service.IAddressService;
import com.dearwang.store01.service.IDistrictService;
import com.dearwang.store01.service.ex.InsertException;
import com.dearwang.store01.service.ex.address.AddressCountLimitException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

//收货地址的实现类
@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;
    /*要使该业务能够都添加地址信息，需要调用地址的业务层接口服务*/
    @Autowired
    private IDistrictService districtService;

    @Value("${user.address.max-count}")
    private Integer maxCount;

    /*增加收货地址*/
    @Override
    public void addNewAddress(Address address, Integer uid, String username) {
        /*调用收货地址的统计方法*/
        Integer count = addressMapper.countByUid(uid);
        if (count >= maxCount) {
            throw new AddressCountLimitException("用户数据超出限制");
        }

        /*对address对象进行补全*/
        String provinceName = districtService.getNameByCode(address.getProvinceCode());
        String cityName = districtService.getNameByCode(address.getCityCode());
        String areaName = districtService.getNameByCode(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);


        /*补全日志*/
        address.setUid(uid);
        Integer isDefault = count == 0 ? 1 : 0;//1默认地址，0不默认
        address.setIsDefault(isDefault);
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        address.setModifiedTime(new Date());
        address.setCreatedTime(new Date());
        /*插入地址*/
        Integer rows = addressMapper.insert(address);
        if (rows != 1) {
            throw new InsertException("插入时发生了错误");
        }
    }
}
