package com.xuuuuu.aopdemo;

import org.aspectj.lang.ProceedingJoinPoint;

public class MyBook {
  //这些都是通知（Advice）
  public void before1() {
      System.out.println("前置增强......");
  }

  public void after1() {
      System.out.println("后置增强......");
  }

  //环绕通知
  public void around1(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
      //方法之前
      System.out.println("方法之前.....");

      //执行被增强的方法
      proceedingJoinPoint.proceed();

      //方法之后
      System.out.println("方法之后.....");
  }
}
