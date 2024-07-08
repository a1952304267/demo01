package com.dearwang.store01.mapper;

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
public class DistrictMapperTests {
    @Autowired
    private DistrictMapper districtMapper;

    @Test
    public void insert() {
        List<District> list = districtMapper.findByParentId("110100");
        for (District d : list) {
            System.out.println(d);
        }
    }

    @Test
    public void findByCode() {
        String name = districtMapper.findNameByCode("110100");
        System.out.println(name);
    }
}
