package com.example.demo.dao;

import com.example.demo.entity.Person;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PersonDao {

    @Select("select * from person")
    @Results({
            @Result(id=true,column="openid",property="openid"),
            @Result(column="user_name",property="userName"),
            @Result(column="user_icon",property="userIcon"),
            @Result(column = "date_time",property = "dateTime"),
            @Result(column = "date_count",property = "dateCount"),
            @Result(column = "type",property = "type")
    })
    List<Person> findAll();

    @Insert("insert into person(openid) values(#{openid})")
    void insert(Person person);
}
