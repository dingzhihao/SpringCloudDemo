package com.hao.controller;

import com.hao.service.remote.FeignTestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/feign")
public class FeignTestController {

    @Resource
    private FeignTestService feignTestService;

    @RequestMapping(value = "/test", method = {RequestMethod.GET})
    public String test(String name) {
        return feignTestService.feignTest(name);
    }

}