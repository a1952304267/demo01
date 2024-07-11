package com.dearwang.store01.service;


import com.dearwang.store01.entity.Address;
import com.dearwang.store01.entity.District;
import com.dearwang.store01.entity.User;
import com.dearwang.store01.service.ex.ServiceException;
import com.dearwang.store01.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//标记为测试类
@SpringBootTest
//启动单元测试类，需要传参必须是SpringRunner实例类型class
@RunWith(SpringRunner.class)
public class AddressServiceTests {
    @Autowired
    private IAddressService addressService;
    @Autowired
    private IDistrictService districtService;

    @Test
   public void addNewAddress() {
            District district =new District();
            Address address = new Address();
            address.setPhone("12300001111");
            address.setAddname("礼物");
            addressService.addNewAddress(address,99,"管理员");
    }
    @Test
    public void setDefault() {
        addressService.setDefault(15,12,"管理员");
    }
    @Test
    public void deleteAddress() {
      addressService.deleteAddress(23,51,"管理员");
    }

    @Test
    public void updateAddress() {
//        District district =new District();
        Address address = new Address();
        address.setPhone("11111122222");
        address.setAddname("礼物");
        addressService.updateAddress(address,24,"管理员");
    }

}
