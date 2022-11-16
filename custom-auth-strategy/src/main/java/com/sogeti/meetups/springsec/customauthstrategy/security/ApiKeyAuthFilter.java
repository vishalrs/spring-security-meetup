package com.sogeti.meetups.springsec.customauthstrategy.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;


//@Component
public class ApiKeyAuthFilter implements Filter {

    private final String TOKEN_HEADER = "x-api-key";


    private AuthenticationManager authenticationManager;

    public ApiKeyAuthFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        final String tokenValue = getTokenValue((HttpServletRequest)request);
        ApiKeyAuthToken authTokenRequest = new ApiKeyAuthToken(tokenValue);
        Authentication authTokenResult = authenticationManager.authenticate(authTokenRequest);
        if (authTokenResult.isAuthenticated()){
            // If I have fully authentication instance, add to the security context
            // do not think about the security context for now..
            SecurityContextHolder.getContext().setAuthentication(authTokenResult);
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private String getTokenValue(HttpServletRequest req) {
        return Collections.list(req.getHeaderNames()).stream()
                          .filter(header -> header.equalsIgnoreCase(TOKEN_HEADER))
                          .map(header -> req.getHeader(header))
                          .findFirst()
                          .orElse(null);
    }
}
