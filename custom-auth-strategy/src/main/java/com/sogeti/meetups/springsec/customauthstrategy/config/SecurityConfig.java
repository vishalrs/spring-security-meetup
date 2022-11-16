package com.sogeti.meetups.springsec.customauthstrategy.config;

import com.sogeti.meetups.springsec.customauthstrategy.security.ApiKeyAuthFilter;
import com.sogeti.meetups.springsec.customauthstrategy.security.ApiKeyAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig  {


    @Bean
    public SecurityFilterChain configure(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        return http
                .authorizeRequests()
                .antMatchers("/welcome").hasRole("ORG_USER")
                .and()
                .addFilterBefore(new ApiKeyAuthFilter(authenticationManager), AnonymousAuthenticationFilter.class)
                .build();
    }


    @Bean
    public AuthenticationManager authenticationManager(ApiKeyAuthProvider apiKeyAuthProvider) {
        return new ProviderManager(Arrays.asList(apiKeyAuthProvider));
    }

    @Bean
    public AuthenticationProvider apiKeyAuthenticationProvider() {
        return new ApiKeyAuthProvider();
    }

}
