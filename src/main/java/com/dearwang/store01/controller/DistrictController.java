package com.dearwang.store01.controller;

import com.dearwang.store01.entity.District;
import com.dearwang.store01.service.IDistrictService;
import com.dearwang.store01.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("district")
public class DistrictController extends BaseController {
    @Autowired
    private IDistrictService districtService;

    /*拦截主请求下的所有请求*/
    @RequestMapping({"/",""})
    public JsonResult<List<District>> getByParent(String parent) {
        List<District> list = districtService.getByParent(parent);
        return new JsonResult<>(OK, list);
    }
}
