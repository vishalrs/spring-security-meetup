package com.sogeti.meetups.springsec.csrf.customerapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import java.util.Arrays;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
       return  http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf().disable()
                /*.requiresChannel(channel -> channel
                       .anyRequest().requiresSecure()
                )*/
               .build();
    }

    @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
                               .username("user")
                               .password("{noop}password")
                               .roles("USER")
                               .build();
        return new InMemoryUserDetailsManager(user);
    }

   /* @Bean
    public StrictHttpFirewall httpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowedHttpMethods(Arrays.asList("GET"));
        return firewall;
    }*/
}
