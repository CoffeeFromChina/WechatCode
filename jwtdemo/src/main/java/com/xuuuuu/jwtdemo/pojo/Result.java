package com.xuuuuu.jwtdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Description 统一返回格式工具实体类
 * @ClassName Result.java
 * @Author：Xuuuuucong
 * @Version 1.0
 * @Date：2020/6/28 16:30
 **/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
	private Integer code;
	private String msg;
	private T data;
}
