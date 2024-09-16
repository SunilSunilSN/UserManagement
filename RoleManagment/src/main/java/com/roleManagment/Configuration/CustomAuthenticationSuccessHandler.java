package com.roleManagment.Configuration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.roleManagment.Service.UserDataService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {


    @Autowired
    private UserDataService userDataService;

    public CustomAuthenticationSuccessHandler(UserDataService userDataService) {
        this.userDataService = userDataService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        String redirectUrl = "/user/" + userDataService.getCurrentUserId(); 
        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }
}
