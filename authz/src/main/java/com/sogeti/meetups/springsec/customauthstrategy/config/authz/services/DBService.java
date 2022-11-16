package com.sogeti.meetups.springsec.customauthstrategy.config.authz.services;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class DBService {

    @PreAuthorize("hasRole('ADMIN')")
    public void someDBOperation(){
        System.out.println("Call to some DB operation allowed");
    }
}
