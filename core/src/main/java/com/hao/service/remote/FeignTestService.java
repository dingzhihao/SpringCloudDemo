package com.hao.service.remote;

import com.hao.fallback.FeignTestHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service2", fallback = FeignTestHystrix.class)
public interface FeignTestService {

    @RequestMapping("/feignTest")
    String feignTest(@RequestParam("name") String name);

}