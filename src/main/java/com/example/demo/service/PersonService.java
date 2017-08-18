package com.example.demo.service;

import com.example.demo.entity.Person;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PersonService {

    List<Person> findAllPerson();

    @Transactional
    int createPerson();
}
