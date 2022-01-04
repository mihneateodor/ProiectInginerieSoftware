package com.proiect.proiect.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        CustomPersoanaDetails userDetails = (CustomPersoanaDetails) authentication.getPrincipal();
        System.out.println("Username " + userDetails.getUsername());

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        authorities.forEach(auth -> System.out.println(auth.getAuthority()));

        super.onAuthenticationSuccess(request,response,authentication);


    }

}
