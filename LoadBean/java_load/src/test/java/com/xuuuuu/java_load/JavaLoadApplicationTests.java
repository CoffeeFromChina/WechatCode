package com.xuuuuu.java_load;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = StudentConfig.class)
public class JavaLoadApplicationTests {

    @Autowired
    private Student student;

    @Test
    public void test(){
        student.setAge(18);
        student.setName("Xuuuuu");
        student.say();
    }
}
