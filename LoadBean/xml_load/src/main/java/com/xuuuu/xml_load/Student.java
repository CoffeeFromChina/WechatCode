package com.xuuuu.xml_load;

import org.springframework.stereotype.Component;

@Component
public class Student {
    private int age;
    private String name;

    public void setAge(int age){
        this.age = age;
    }
    public void setName(String name){
        this.name = name;
    }

    public int getAge(){
        return age;
    }
    public String getName(){
        return name;
    }

    public void say(){
        System.out.println("hello word；" + "\n"
            + "Your name is：" + name +"；\n"
            + "Your age is：" + age +"；\n"
            + "PASS：我最牛逼"
        );
    }

}
