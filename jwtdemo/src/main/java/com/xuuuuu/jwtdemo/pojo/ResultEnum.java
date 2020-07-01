package com.xuuuuu.jwtdemo.pojo;

/**
 * @Description 返回状态码枚举
 * @Author：Xuuuuucong
 * @Version 1.0
 * @Date：2020/6/28 16:34
 **/
public enum  ResultEnum {
	//这里是可以自己定义的，方便与前端交互即可
	UNKNOWN_ERROR(-1,"未知错误"),
	SUCCESS(200,"成功"),
	USER_NOT_EXIST(404,"用户不存在"),
	USER_IS_EXISTS(2,"用户已存在"),
	DATA_IS_NULL(404,"数据为空");

	private Integer code;
	private String msg;

	ResultEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;

	}
}
