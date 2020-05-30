package com.darryl.snow.marisa.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.darryl.snow.marisa.entity.User;
import com.darryl.snow.marisa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author darryl
 * @since 2020-05-30
 */
@RestController
@RequestMapping("/marisa/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/queryPage")
	public ResponseEntity<IPage<User>> queryPage(Integer currentPage, Integer pageSize) {
		IPage<User> pageCondition = new Page<>();
		// 当前页
		pageCondition.setCurrent(currentPage);
		// 每页条数
		pageCondition.setSize(pageSize);
		IPage<User> userPage = userService.page(pageCondition);
		return new ResponseEntity<>(userPage, HttpStatus.OK);
	}
}
