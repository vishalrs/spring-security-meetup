package com.sogeti.meetups.springsec.basicauth.web.controllers;

import com.github.javafaker.Faker;
import com.sogeti.meetups.springsec.basicauth.model.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @GetMapping
    public ResponseEntity<List<Person>> list() {
        Faker faker = new Faker();
        List<Person> persons = IntStream.range(0, 5).mapToObj(i -> {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = faker.internet().emailAddress(firstName);
            return new Person(firstName, lastName,email);
        }).collect(Collectors.toList());
        return new ResponseEntity(persons, HttpStatus.OK);
    }
}
