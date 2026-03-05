package com.example;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

// This annotation tells Tomcat to run this bouncer for EVERY request
@WebFilter("/*") 
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false); // Get session, but don't create a new one

        String loginURI = req.getContextPath() + "/login.jsp";
        String loginServletURI = req.getContextPath() + "/login";

        // Logic: Is the user logged in? 
        boolean loggedIn = (session != null && session.getAttribute("user") != null);
        
        // Logic: Is the user just trying to get to the login page?
        boolean loginRequest = req.getRequestURI().equals(loginURI) || req.getRequestURI().equals(loginServletURI);
        
        // Logic: Is the user asking for an image (giraffe.png, fox.png)?
        boolean resourceRequest = req.getRequestURI().endsWith(".png") || req.getRequestURI().endsWith(".css");

        if (loggedIn || loginRequest || resourceRequest) {
            // "Pass through" - let them go to the destination
            chain.doFilter(request, response);
        } else {
            // "Access Denied" - kick them back to the login page
            res.sendRedirect(loginURI);
        }
    }
}
