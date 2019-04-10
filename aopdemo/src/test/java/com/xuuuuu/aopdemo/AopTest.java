package com.xuuuuu.aopdemo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
 
public class AopTest {
  @Test
  public void testService() {
      ApplicationContext context = new ClassPathXmlApplicationContext("aop.xml");
      Book book = (Book) context.getBean("book");
      book.add();
  }
}
