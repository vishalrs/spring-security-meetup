package com.sogeti.meetups.springsec.customauthstrategy.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;



@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ApiKeyUser  {

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String apiKey;
    private List<? extends GrantedAuthority> grantedAuthorities;

}
