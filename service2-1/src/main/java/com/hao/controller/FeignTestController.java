package com.hao.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignTestController {

    @RequestMapping("/feignTest")
    public String feignTest(String name) {
        return "Service2-1: Hello " + name;
    }

}