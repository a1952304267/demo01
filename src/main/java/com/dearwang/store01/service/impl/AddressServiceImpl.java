package com.dearwang.store01.service.impl;


import com.dearwang.store01.entity.Address;
import com.dearwang.store01.entity.District;
import com.dearwang.store01.mapper.AddressMapper;
import com.dearwang.store01.service.IAddressService;
import com.dearwang.store01.service.IDistrictService;
import com.dearwang.store01.service.ex.InsertException;
import com.dearwang.store01.service.ex.UpdateException;
import com.dearwang.store01.service.ex.address.AccessDeniedException;
import com.dearwang.store01.service.ex.address.AddressCountLimitException;
import com.dearwang.store01.service.ex.address.AddressNotFoundException;
import com.dearwang.store01.service.ex.address.DeleteException;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    /*返回查询到的列表*/
    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> list = addressMapper.findByUid(uid);
        /*去除多余数据*/
        for (Address address : list) {
//            address.setAid(null);
//            address.setUid(null);
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setAreaCode(null);
            address.setTel(null);
            address.setIsDelete(null);
            address.setCreatedTime(null);
            address.setModifiedTime(null);
            address.setCreatedUser(null);
            address.setModifiedUser(null);
        }
        return list;
    }

    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        Address address = addressMapper.findByAid(aid);
        if (address == null) {
            throw new AddressNotFoundException("收货地址不存在");
        }
//        检测收货地址的归属
        if (!address.getUid().equals(uid)) {
            throw new AccessDeniedException("非法数据访问");
        }
        /*设置非默认地址*/
        Integer rows = addressMapper.updateNonDefault(uid);
        if (rows < 1) {
            throw new UpdateException("设置地址时出现错误");
        }
        Integer row1 = addressMapper.updateDefault(aid, username, new Date());
        if (row1 != 1) {
            throw new UpdateException("设置地址时出现错误");
        }

    }

    @Override
    public void deleteAddress(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if (result == null) {
            throw new AddressNotFoundException("收货地址不存在");
        }
        if (!result.getUid().equals(uid)) {
            throw new AccessDeniedException("非法数据访问");
        }
        Integer rows = addressMapper.deleteByAid(aid);
        if (rows != 1) {
            throw new DeleteException("删除失败，产生未知错误");
        }

        Integer count = addressMapper.countByUid(uid);
        if (count == 0) {
            /*终止程序*/
            return;
        }
        if (result.getIsDefault() == 0) {
            /*终止程序*/
            return;
        }
        Address address = addressMapper.findNewModifiedTime(uid);
        rows = addressMapper.updateDefault(address.getAid(), username, new Date());
        if (rows != 1) {
            throw new UpdateException("更新时产生位置错误");
        }
    }

    @Override
    public void updateAddress(Address address, Integer aid, String username) {
        /*数据是否存在*/
        Address result = addressMapper.findByAid(aid);
        if (result == null){
            throw new AddressNotFoundException("用户地址数据已不存在");
        }
        /*对address对象进行补全*/
        address.setProvinceName(districtService.getNameByCode(address.getProvinceCode()));
        address.setCityName(districtService.getNameByCode(address.getCityCode()));
        address.setAreaName(districtService.getNameByCode(address.getAreaCode()));


        /*补全日志*/
        address.setAid(aid);//这个字段不能省略，我一开始想省略这个字段，但数据中时非空，更新数据时会强制设置为空值
        address.setUid(result.getUid());
        address.setModifiedUser(username);
        address.setModifiedTime(new Date());
        /*更新地址*/
        Integer rows = addressMapper.updateAddress(address);
        if (rows != 1) {
            throw new UpdateException("更新时发生了错误");
        }
    }
}
