package com.dearwang.store01.service;


import com.dearwang.store01.entity.Address;
import com.dearwang.store01.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

//标记为测试类
@SpringBootTest
//启动单元测试类，需要传参必须是SpringRunner实例类型class
@RunWith(SpringRunner.class)
public class DistrictServiceTests {
    @Autowired
    private IDistrictService districtService;

    @Test
    public void getDistrict() {
        /*86代表中国*/
//        List<District> list = districtService.getByParent("86");
//        for (District d : list) {
//            System.err.println(d);
//        }
        /*110100代表北京*/
        List<District> list = districtService.getByParent("110100");
        for (District d : list) {
            System.err.println(d);
        }
    }
}
