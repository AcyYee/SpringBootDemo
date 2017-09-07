package com.example.demo.controller;

import com.example.demo.common.HttpSend;
import com.example.demo.common.UUIDUtil;
import com.example.demo.common.WXConfig;
import com.example.demo.entity.Person;
import com.example.demo.service.PersonService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private PersonService personService;

    @RequestMapping("persons")
    public List<Person> persons(){
        return personService.findAllPerson();
    }

    @PostMapping
    public void loadState(String state,HttpSession session){
        session.setAttribute("state",state);
    }

    @RequestMapping("test")
    public int test(String code ,String state){
        String takenUrl = WXConfig.ACCESS_TOKEN;
        takenUrl= takenUrl.replace("APPID", WXConfig.WX_WEB_APP_ID);
        takenUrl= takenUrl.replace("SECRET", WXConfig.WX_WEB_APP_SECRET);
        takenUrl= takenUrl.replace("CODE", code);
        String result = HttpSend.sendGet(takenUrl,"");
        return personService.createPerson(result);
    }


}
