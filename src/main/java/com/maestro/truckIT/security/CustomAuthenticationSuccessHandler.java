package com.maestro.truckIT.security;

import com.maestro.truckIT.model.Users;
import com.maestro.truckIT.service.UsersService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("\n\nIn CustomAuthenticationSuccessHandler\n\n");
        String username = authentication.getName();
        System.out.println("username=" + username);
        Users theUser = UsersService.findByUsername(username);

        HttpSession session = request.getSession();
        session.setAttribute("user", theUser);

        response.sendRedirect(request.getContextPath() + "/");
    }
}

