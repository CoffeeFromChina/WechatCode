package com.xuuuuu.auto_load;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutoLoadApplicationTests {

    @Test
    public void beanTest(){
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("LoadBean.xml");
        Student st = (Student)ac.getBean("student");
        st.setAge(18);
        st.setName("Xuuuuu");
        st.say();
    }

}
