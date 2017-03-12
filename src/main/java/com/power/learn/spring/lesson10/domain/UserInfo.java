package com.power.learn.spring.lesson10.domain;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * Created by shenli on 2017/3/10.
 */
public class UserInfo implements Serializable{

    @NotNull
    @DecimalMax("99")
    @DecimalMin("18")
    private int age;

    @NotNull
    @Size(min = 6,max = 18)
    @Pattern(regexp = "^[A-Za-z0-9]+$")
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
