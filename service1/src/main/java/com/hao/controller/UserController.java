package com.hao.controller;

import com.google.common.util.concurrent.RateLimiter;
import com.hao.constant.ResponseMessage;
import com.hao.entity.User;
import com.hao.exception.CustomException;
import com.hao.service.UserService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Api("userController")
@RestController
@RequestMapping("/user")
public class UserController {

    // LogBack
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    private RateLimiter rateLimiter = RateLimiter.create(1000);

    @ApiOperation("根据id查询指定用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "int", paramType = "query", example = "1")
    @ApiResponses({
            @ApiResponse(code = 404, message = "客户端错误"),
            @ApiResponse(code = 500, message = "服务器错误")
    })
    @RequestMapping(value = "/getUser", method = {RequestMethod.GET})
    public User getUser(@RequestParam Integer id) throws CustomException {
        boolean isValid = rateLimiter.tryAcquire((long) 1, TimeUnit.SECONDS);
        if (!isValid)
            throw new CustomException(ResponseMessage.SERVICE_BUSY);

        User user = null;
        try {
            user = userService.getUser(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user == null)
            throw new CustomException(ResponseMessage.USER_QUERY_ERROR);
        logger.info(user.toString());
        return user;
    }

}