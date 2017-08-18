package com.example.demo.controller;

import com.example.demo.common.YzyValues;
import com.example.demo.entity.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private YzyValues yzyValues;

    @Autowired
    private PersonService personService;

    @RequestMapping("show")
    public String show(){
        return yzyValues.toString();
    }

    @RequestMapping("persons")
    public List<Person> persons(){
        return personService.findAllPerson();
    }

    @RequestMapping("test")
    public int test(){
        return personService.createPerson();
    }

}
