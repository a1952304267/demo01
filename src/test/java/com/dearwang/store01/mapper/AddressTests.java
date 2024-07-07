package com.dearwang.store01.mapper;

import com.dearwang.store01.entity.Address;
import com.dearwang.store01.entity.User;
import com.dearwang.store01.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

//标记为测试类
@SpringBootTest
//启动单元测试类，需要传参必须是SpringRunner实例类型class
@RunWith(SpringRunner.class)
public class AddressTests {
    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void insert() {
        Address address = new Address();
        address.setUid(99);
        address.setPhone("12300000000");
        address.setTname("女朋友");
        addressMapper.insert(address);
        System.out.println(address);
    }

    @Test
    public void countByUid(){
        Integer count = addressMapper.countByUid(9);
        System.out.println(count);
    }

}
