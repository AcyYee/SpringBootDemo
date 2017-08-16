package com.example.demo.service.impl;

import com.example.demo.dao.PersonDao;
import com.example.demo.entity.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;

    public List<Person> findAllPerson(){
        return personDao.findAll();
    }

}

