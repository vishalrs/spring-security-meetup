package com.sogeti.meetups.springsec.customauthstrategy.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/welcome")
    public ResponseEntity<String> getWelcomeMessage(){
        return new ResponseEntity<>("Welcome ApiKey Security", HttpStatus.OK);
    }
}
