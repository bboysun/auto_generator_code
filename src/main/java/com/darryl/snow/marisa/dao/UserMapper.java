package com.darryl.snow.marisa.dao;

import com.darryl.snow.marisa.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * user Mapper 接口
 * @author darryl
 * @since 2020-05-30
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}