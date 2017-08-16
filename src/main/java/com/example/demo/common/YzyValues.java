package com.example.demo.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "yzy")
public class YzyValues {

    private Integer age;

    private String name;

    private String jbcd;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJbcd() {
        return jbcd;
    }

    public void setJbcd(String jbcd) {
        this.jbcd = jbcd;
    }

    @Override
    public String toString() {
        return "YzyValues{" +
                "年龄=" + age +
                ", 姓名='" + name + '\'' +
                ", jb长度='" + jbcd + '\'' +
                '}';
    }
}
