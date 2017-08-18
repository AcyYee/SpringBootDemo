package com.example.demo.service.impl;

import com.example.demo.dao.PersonDao;
import com.example.demo.entity.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;

    @Override
    public List<Person> findAllPerson(){
        return personDao.findAll();
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public int createPerson(){
        Person person1 = new Person();
        Person person2 = new Person();
        person1.setOpenid("qweqeqwew");
        personDao.insert(person1);
        personDao.insert(person2);
        return 1;
    }

}

