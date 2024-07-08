package com.dearwang.store01.controller;

import com.dearwang.store01.entity.Address;
import com.dearwang.store01.service.IAddressService;
import com.dearwang.store01.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("address")
public class AddressController extends BaseController {
    @Autowired
    private IAddressService addressService;

    @RequestMapping("insert_address")
    public JsonResult<Void> addNewAddress(Address address, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.addNewAddress(address, uid, username);
        return new JsonResult<>(OK);
    }

}
