package com.xuuuuu.java_load;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//此类作为 Bean 的配置类。其余的类就不必添加类似 @Component 的配置
@Configuration
public class StudentConfig {
    @Bean
    public Student student(){
        return new Student();
    }
}
