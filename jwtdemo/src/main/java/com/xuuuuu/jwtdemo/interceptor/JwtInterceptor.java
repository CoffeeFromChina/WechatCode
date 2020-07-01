package com.xuuuuu.jwtdemo.interceptor;

import com.xuuuuu.jwtdemo.Util.JwtTokenUtil;
import com.xuuuuu.jwtdemo.pojo.Audience;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description URL 拦截器
 * @ClassName JwtInterceptor.java
 * @Author：Xuuuuucong
 * @Version 1.0
 * @Date：2020/6/28 16:16
 **/
@Slf4j
public class JwtInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private Audience audience;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 如果不是映射到方法直接拒绝
		if(!(handler instanceof HandlerMethod)){
			return false;
		}
		String token = request.getHeader(JwtTokenUtil.AUTH_HEADER_KEY);

		String role = (String) jwtTokenUtil.parseJWT(token, audience.getBase64Secret()).get("role");

		//检验权限
		if((role).equals("admin")){
			return true;
		}else {
			log.info("{}", "权限不足");
			// 向客户端返回错误信息
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			JSONObject res = new JSONObject();
			res.put("status","401");
			res.put("msg","权限不足");
			PrintWriter out = null ;
			out = response.getWriter();
			out.write(res.toString());
			out.flush();
			out.close();

			return false;
		}
	}
}
