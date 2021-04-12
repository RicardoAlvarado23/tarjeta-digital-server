package com.cmacsullana.tarjetadigital.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The Class CorsFilter.
 *
 * @author riap
 */
public class CorsFilter implements Filter {

    
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods","GET,HEAD,POST,PUT,DELETE,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers","Origin, X-Requested-With, Content-Type, Accept, Token, Pragma, Cache-Control, If-Modified-Since, authorization, Enctype");
        
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        if (!"OPTIONS".equalsIgnoreCase(httpRequest.getMethod())) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        System.out.println("Do Filter Cors Success");
    }

    
    public void destroy() {

    }

}