package com.sogeti.meetups.springsec.Filters;

import com.sogeti.meetups.springsec.components.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("auditFilter")
public class AuditRequestFilter implements Filter {

    @Autowired
    private UtilService utilService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response, final FilterChain filterChain)
            throws IOException, ServletException {
        System.out.println("AuditFilter Called");
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;

        System.out.println("Utilservice bean:" + utilService);
        if(utilService!=null){
            System.out.println(utilService.getDefaultMessage());
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
