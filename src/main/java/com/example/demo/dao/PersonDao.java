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
            @Result(id=true,column="person_id",property="personId"),
            @Result(column="openid",property="openid")
    })
    List<Person> findAll();

    @Insert("insert into person(openid) values(#{openid})")
    void insert(Person person);
}
