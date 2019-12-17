package com.hao.fallback;

import com.hao.constant.ResponseMessage;
import com.hao.service.remote.FeignTestService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class FeignTestHystrix implements FeignTestService {

    @Override
    public String feignTest(@RequestParam("name") String name) {
        return ResponseMessage.SERVICE_BUSY;
    }

}