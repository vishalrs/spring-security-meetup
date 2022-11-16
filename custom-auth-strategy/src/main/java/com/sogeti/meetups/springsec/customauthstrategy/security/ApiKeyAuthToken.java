package com.sogeti.meetups.springsec.customauthstrategy.security;

import com.sogeti.meetups.springsec.customauthstrategy.model.ApiKeyUser;
import org.springframework.security.authentication.AbstractAuthenticationToken;

public class ApiKeyAuthToken extends AbstractAuthenticationToken {

    private ApiKeyUser user;


    private String apiKey;


    public ApiKeyAuthToken(String token) {
        super(null);
        this.apiKey = token;
        this.user = null;
        setAuthenticated(false);
    }

    public ApiKeyAuthToken(ApiKeyUser user) {
        super(user.getGrantedAuthorities());
        this.user = user;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return getUser().getApiKey();
    }

    @Override
    public Object getPrincipal() {
        return getUser();
    }

    public ApiKeyUser getUser(){
        return user;
    }

    public String getApiKey() {
        return apiKey;
    }


}
