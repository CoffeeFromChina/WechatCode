package com.xuuuuu.jwtdemo.controller;

import com.xuuuuu.jwtdemo.Util.JwtTokenUtil;
import com.xuuuuu.jwtdemo.Util.ResultUtil;
import com.xuuuuu.jwtdemo.pojo.Audience;
import com.xuuuuu.jwtdemo.pojo.Result;
import com.xuuuuu.jwtdemo.pojo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description 控制器
 * @ClassName TestController.java
 * @Author：Xuuuuucong
 * @Version 1.0
 * @Date：2020/6/28 16:26
 **/
@Slf4j
@RestController
public class TestController {
	@Autowired
	private Audience audience;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@PostMapping("/login")
	public Result adminLogin(HttpServletResponse response,
	                         @RequestParam("id") String id,
	                         @RequestParam("name") String name,
	                         @RequestParam("username") String username,
	                         @RequestParam("password") String password,
							 @RequestParam("role") String role) {

		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setUserName(username);
		user.setPassword(password);
		user.setRole(role);

		// 创建token
		String token = jwtTokenUtil.createToken(user);
		log.info("### 登录成功, token={} ###", token);
		// 将token放在响应头
		response.setHeader(JwtTokenUtil.AUTH_HEADER_KEY, JwtTokenUtil.TOKEN_PREFIX + token);

		return ResultUtil.success(token);
	}

	@GetMapping("/getUserInfo")
	public Result login(HttpServletRequest request) {
		log.info("token:{}",request.getHeader("Authorization"));
		String token = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);

		User user = new User();
		user.setId((String) jwtTokenUtil.parseJWT(token, audience.getBase64Secret()).get("id"));
		user.setName((String) jwtTokenUtil.parseJWT(token, audience.getBase64Secret()).get("name"));
		user.setUserName(jwtTokenUtil.getUsername(token, audience.getBase64Secret()));
		user.setRole((String) jwtTokenUtil.parseJWT(token, audience.getBase64Secret()).get("role"));
		user.setPassword("******");

		log.info("当前用户信息id=" + user.getId() + ",name=" + user.getName() + ",userName=" + user.getUserName() + ",role=" + user.getRole());
		return ResultUtil.success(user);
	}

}
