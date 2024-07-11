package com.dearwang.store01.controller;

        import com.dearwang.store01.entity.Address;
        import com.dearwang.store01.entity.District;
        import com.dearwang.store01.service.IAddressService;
        import com.dearwang.store01.util.JsonResult;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

        import javax.servlet.http.HttpSession;
        import java.util.List;

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

    @RequestMapping({"", "/"})
    public JsonResult<List<Address>> getByUid(HttpSession session) {
        Integer uid = getUidFromSession(session);
        List<Address> list = addressService.getByUid(uid);
        return new JsonResult<>(OK, list);
    }

    @RequestMapping("{aid}/set_default")
    public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid,
                                       HttpSession session) {
        Integer uid = getUidFromSession(session);
        String  username = getUsernameFromSession(session);
        addressService.setDefault(aid, uid,username);
        return new JsonResult<>(OK);
    }

    @RequestMapping("{aid}/delete")
    public JsonResult<Void> delete(@PathVariable("aid") Integer aid,
                                  HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        addressService.deleteAddress(aid, uid, username);
        return new JsonResult<>(OK);
    }

    @RequestMapping("{aid}/update")
    public JsonResult<Void> update(@PathVariable("aid") Integer aid,
                                    Address address,
                                    HttpSession session) {
        address.setUid(getUidFromSession(session));
        String username = getUsernameFromSession(session);
        addressService.updateAddress(address, aid, username);
        return new JsonResult<>(OK);
    }

}
