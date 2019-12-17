package com.hao.controller;

import com.hao.service.impl.RedissonService;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/RedissonTest")
public class RedissonController {

    @Autowired
    private RedissonService redissonService;

    @GetMapping("/getLock")
    public String getLock(String lockKey) {
        RLock rLock = redissonService.getLock(lockKey);
        return rLock.getName();
    }

    @GetMapping("/getList")
    public List<String> getList(String key) {
        return redissonService.getList(key);
    }

    @GetMapping("/addList")
    public boolean addList(String key, String value) {
        return redissonService.addList(key, value);
    }

}