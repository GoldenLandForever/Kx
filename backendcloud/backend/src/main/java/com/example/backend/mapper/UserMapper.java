package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
