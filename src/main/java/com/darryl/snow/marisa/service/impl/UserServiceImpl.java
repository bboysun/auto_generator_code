package com.darryl.snow.marisa.service.impl;

import com.darryl.snow.marisa.entity.User;
import com.darryl.snow.marisa.dao.UserMapper;
import com.darryl.snow.marisa.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author darryl
 * @since 2020-05-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

	@Override
	public void test() {
		System.out.println("test");
	}
}
