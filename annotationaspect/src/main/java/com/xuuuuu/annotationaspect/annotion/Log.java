package com.xuuuuu.annotationaspect.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description TODO
 * @ClassName AnnotionLog.java
 * @Author：Xuuuuucong
 * @Version 1.0
 * @Date：2021/1/22 16:22
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
	String value() default "";
}
