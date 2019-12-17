package com.hao.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignTestController {

    @RequestMapping("/feignTest")
    public String feignTest(String name) {
//        try {
////            Thread.sleep(2000);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
        return "Service2: Hello " + name;
    }

}