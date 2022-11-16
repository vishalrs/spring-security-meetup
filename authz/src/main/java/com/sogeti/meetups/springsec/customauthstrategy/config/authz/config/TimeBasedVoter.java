package com.sogeti.meetups.springsec.customauthstrategy.config.authz.config;

import org.springframework.cglib.core.Local;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

public class TimeBasedVoter implements AccessDecisionVoter {
    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object object, Collection collection) {
        int vote = authentication.getAuthorities().stream()
                      .map(GrantedAuthority::getAuthority)
                      .filter(r -> "ROLE_USER".equals(r)
                              && !isLoginWithinWorkingHours(LocalTime.now()))
                      .findAny()
                      .map(s -> ACCESS_DENIED)
                      .orElseGet(() -> ACCESS_ABSTAIN);
        return vote;
    }

    @Override
    public boolean supports(Class clazz) {
        return true;
    }

    //Check to see if the current time is within the working hours
    private boolean isLoginWithinWorkingHours(LocalTime currentTime){
        LocalTime now = currentTime;
        LocalTime start = LocalTime.of(9,0,0);
        LocalTime end = LocalTime.of(17,0,0);
        return ( ! now.isBefore( start ) ) && now.isBefore( end ) ;
    }

}
