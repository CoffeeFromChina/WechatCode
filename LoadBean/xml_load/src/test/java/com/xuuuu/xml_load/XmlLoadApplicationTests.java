package com.xuuuu.xml_load;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlLoadApplicationTests {

    @Test
    public void  test(){
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("LoadBean.xml");
        Student student = (Student) ac.getBean("student");
        student.setAge(18);
        student.setName("Xuuuuu");
        student.say();
    }
}
