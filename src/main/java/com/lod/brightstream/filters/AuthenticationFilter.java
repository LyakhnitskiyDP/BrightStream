package com.lod.brightstream.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


public class AuthenticationFilter implements Filter {
    FilterConfig filterConfig;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response, 
                         FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        
        if (userIsPresentInSession(httpRequest)) 
            chain.doFilter(request, response);
        else
            httpRequest.getRequestDispatcher("/login.jsp")
                       .forward(request, response);
        
        
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }
    
    private boolean userIsPresentInSession(HttpServletRequest request) {
        return request.getSession()
                      .getAttribute("user") != null;
    }
    
}
