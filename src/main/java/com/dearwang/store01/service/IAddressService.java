package com.dearwang.store01.service;

import com.dearwang.store01.entity.Address;
import org.springframework.stereotype.Service;


public interface IAddressService {

    /*同时定义插入和统计
    * 并定义多个数据对象，提升可复用性*/
    void addNewAddress(Address address, Integer uid, String username);
}
