package com.xuuuuu.jwtdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 用户
 * @ClassName User.java
 * @Author：Xuuuuucong
 * @Version 1.0
 * @Date：2020/6/27 15:54
 **/
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private String id;
	private String userName;
	private String name;
	private String password;
	private String role;
}
