package com.sogeti.meetups.springsec.components;

import org.springframework.stereotype.Component;

@Component
public class UtilService {

    public String getDefaultMessage(){
        return "Hello from spring application context!";
    }
}
