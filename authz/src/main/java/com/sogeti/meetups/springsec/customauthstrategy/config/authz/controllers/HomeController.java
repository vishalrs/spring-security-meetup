package com.sogeti.meetups.springsec.customauthstrategy.config.authz.controllers;

import com.sogeti.meetups.springsec.customauthstrategy.config.authz.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class HomeController {

    @Autowired
    DBService dbService;

    @GetMapping("/home")
    public ResponseEntity<Principal> home(Principal principal){
        return new ResponseEntity<>(principal, HttpStatus.OK);
    }

    @GetMapping("/home/products")
    public ResponseEntity<List<String>> home(){
        dbService.someDBOperation();
        return new ResponseEntity<>(List.of("product-1","product-2","product-3"), HttpStatus.OK);
    }
}
