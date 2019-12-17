package com.hao.exception.handler;

import com.alibaba.fastjson.JSONArray;
import com.hao.constant.ResponseMessage;
import com.hao.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Serializable> exceptionHandler(Exception e) {
        Map<String, Serializable> result = new HashMap<>();
        result.put("message", ResponseMessage.SERVICE_BUSY);
        result.put("position", (Serializable) JSONArray.toJSON(e.getStackTrace()));
        logger.error(e.getMessage(), e);
        return result;
    }

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Serializable> customExceptionHandler(CustomException e) {
        Map<String, Serializable> result = new HashMap<>();
        result.put("message", e.getMessage());
        return result;
    }

}