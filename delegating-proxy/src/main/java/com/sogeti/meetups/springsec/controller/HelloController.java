package com.sogeti.meetups.springsec.controller;

import com.sogeti.meetups.springsec.components.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    UtilService utilService;

    @GetMapping
    public String sayHello() {
        return "Hello from Spring MVC Application!";
    }
}