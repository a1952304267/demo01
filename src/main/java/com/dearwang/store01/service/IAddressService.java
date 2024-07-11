package com.dearwang.store01.service;

import com.dearwang.store01.entity.Address;
import com.dearwang.store01.entity.District;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IAddressService {

    /*同时定义插入和统计
    * 并定义多个数据对象，提升可复用性*/
    void addNewAddress(Address address, Integer uid, String username);

    List<Address> getByUid(Integer uid);

    void  setDefault(Integer aid, Integer uid,String username);

    /**
     * 删除用户地址数据
     * @param aid 地址id
     * @param uid 用户id
     * @param username 用户名
     */
    void deleteAddress(Integer aid, Integer uid,String username);

    /**
     *  更新用户地址数据
     * @param address 地址数据
     * @param aid 地址id
     * @param username 用户名
     */
    void updateAddress(Address address, Integer aid, String username);
}
