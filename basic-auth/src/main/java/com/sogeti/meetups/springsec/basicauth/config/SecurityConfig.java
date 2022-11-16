package com.sogeti.meetups.springsec.basicauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity(debug = true)
public class SecurityConfig {


    //Security filter chain setup for endpoint /persons/** with Basic Authentication
    class SecurityConfigBasic {

        @Bean
        public SecurityFilterChain configureFilterChainBasic(HttpSecurity http) throws Exception {
            return http
                    .antMatcher("/persons/**")
                    .httpBasic().and()
                    .authorizeRequests().anyRequest().hasRole("ORG_USER")
                    .and().build();
        }
    }

    class SecurityConfigForm {

        @Bean
        //Security filter chain setup for endpoint /admin/** with Form Based Authentication
        public SecurityFilterChain configureFilterChainForm(HttpSecurity http) throws Exception {

            return http
                    .antMatcher("/admin/**")
                    .formLogin()
                        .loginPage("/login")
                        .permitAll()
                    .and()
                    .authorizeRequests()
                        .anyRequest().hasAnyRole("ORG_USER", "ORG_ADMIN")
                    .and()
                    .build();
        }
    }

    @Bean
    public UserDetailsService users() {
        UserDetails user = User.builder()
                               .username("user")
                               .password("{noop}pass")
                               .roles("ORG_USER")
                               .build();
        UserDetails admin = User.builder()
                                .username("admin")
                                .password("{noop}pass")
                                .roles("ORG_USER", "ORG_ADMIN")
                                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

}
