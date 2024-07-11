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
import java.util.List;

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
        address.setAddname("女朋友");
        addressMapper.insert(address);
        System.out.println(address);
    }

    @Test
    public void countByUid() {
        Integer count = addressMapper.countByUid(9);
        System.out.println(count);
    }

    @Test
    public void findByUid() {
        List<Address> list = addressMapper.findByUid(9);
        System.out.println(list);
    }

    @Test
    public void findByAid() {
        Address address = addressMapper.findByAid(14);
        System.out.println(address);
    }

    @Test
    public void updateNonDefault() {

        System.out.println(addressMapper.updateNonDefault(12));
    }

    @Test
    public void updateDefault() {
        addressMapper.updateDefault(14, "管理员", new Date());

    }

    @Test
    public void deleteByAid() {
        addressMapper.deleteByAid(26);

    }

    @Test
    public void findNewModifiedTime() {
        Address address = addressMapper.findNewModifiedTime(51);
        System.out.println(address);

    }

    @Test
    public void updateAddress() {
        Address address = addressMapper.findByAid(27);
        address.setPhone("123123123123");
        address.setAddname("女朋友");
        address.setModifiedUser("管理员");
        address.setModifiedTime(new Date());
        addressMapper.updateAddress(address);
        System.out.println(address);

//        Address address = new Address();
//        address.setProvinceName("广东省");
//        address.setProvinceCode("440000");
//        address.setCityName("深圳市");
//        address.setCityCode("440300");
//        address.setAreaName("南山区");
//        address.setAreaCode("440305");
//        address.setZip("518000");
//        address.setAddress("科技园路1号");
//        address.setPhone("12345678901");
//        address.setTel("0755-12345678");
//        address.setTag("办公");
//        address.setModifiedUser("testuser");
//        address.setModifiedTime(new Date());
//        address.setAid(27);
//        addressMapper.updateAddress(address);
    }
}
