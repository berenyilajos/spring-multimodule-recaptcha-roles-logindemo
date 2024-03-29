package com.github.mkopylec.recaptcha.security.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFailuresClearingHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    protected final LoginFailuresManager failuresManager;

    public LoginFailuresClearingHandler(LoginFailuresManager failuresManager) {
        this.failuresManager = failuresManager;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    	failuresManager.clearLoginFailures(authentication);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
