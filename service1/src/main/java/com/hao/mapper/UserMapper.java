package com.hao.mapper;

import com.hao.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    public User getUser(Integer id) throws Exception;

}