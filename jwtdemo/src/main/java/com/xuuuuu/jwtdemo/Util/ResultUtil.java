package com.xuuuuu.jwtdemo.Util;

import com.xuuuuu.jwtdemo.pojo.Result;
import com.xuuuuu.jwtdemo.pojo.ResultEnum;

/**
 * @Description 统一返回格式工具类
 * @ClassName ResultUtil.java
 * @Author：Xuuuuucong
 * @Version 1.0
 * @Date：2020/6/28 16:36
 **/
public class ResultUtil {
	// 成功且带数据
	public static Result success(Object object){
		Result result = new Result();
		result.setCode(ResultEnum.SUCCESS.getCode());
		result.setMsg(ResultEnum.SUCCESS.getMsg());
		result.setData(object);
		return result;
	}

	// 成功但不带数据
	public static Result success(){
		return success(null);
	}

	// 失败
	public static Result error(Integer code,String msg){
		Result result = new Result();
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}
}
