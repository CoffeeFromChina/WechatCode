package com.xuuuuu.annotationaspect.aspect;

import com.xuuuuu.annotationaspect.annotion.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description TODO
 * @ClassName LogAspect.java
 * @Author：Xuuuuucong
 * @Version 1.0
 * @Date：2021/1/22 16:27
 **/
@Aspect
@Slf4j
@Component
public class LogAspect {

	@Pointcut("@annotation(com.xuuuuu.annotationaspect.annotion.Log)")
	public void pointcut(){
	}

	@Before("pointcut()")
	public void doBefore(JoinPoint joinPoint) throws Exception {
		// 开始打印请求日志
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		// 获取 @WebLog 注解的描述信息
		String methodDescription = getAspectLogDescription(joinPoint);

		// 打印请求相关参数
		log.info("========================================== Start ==========================================");
		// 打印请求 url
		log.info("URL            : {}", request.getRequestURL().toString());
		// 打印描述信息
		log.info("Description    : {}", methodDescription);
		// 打印 Http method
		log.info("HTTP Method    : {}", request.getMethod());
		// 打印调用 controller 的全路径以及执行方法
		log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
		// 打印请求的 IP
		log.info("IP             : {}", request.getRemoteAddr());
	}

	/**
	 * 在切点之后织入
	 * @throws Throwable
	 */
	@After("pointcut()")
	public void doAfter() throws Throwable {
		// 接口结束后换行，方便分割查看
		log.info("=========================================== End ===========================================" + '\n');
	}

	/**
	 * 环绕
	 * @param proceedingJoinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("pointcut()")
	public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();
		Object result = proceedingJoinPoint.proceed();
		// 执行耗时
		log.info("Time-Consuming : {} ms", System.currentTimeMillis() - startTime);
		return result;
	}

	/**
	 * 获取切面注解的描述
	 *
	 * @param joinPoint 切点
	 * @return 描述信息
	 * @throws Exception
	 */
	public String getAspectLogDescription(JoinPoint joinPoint)
			throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		StringBuilder description = new StringBuilder("");
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					description.append(method.getAnnotation(Log.class).value());
					break;
				}
			}
		}
		return description.toString();
	}
}
