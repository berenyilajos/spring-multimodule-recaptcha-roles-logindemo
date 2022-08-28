package com.github.mkopylec.recaptcha.security;

import com.github.mkopylec.recaptcha.RecaptchaProperties;
import com.github.mkopylec.recaptcha.security.login.FormLoginConfigurerEnhancer;
import com.github.mkopylec.recaptcha.security.login.LoginFailuresClearingHandler;
import com.github.mkopylec.recaptcha.security.login.LoginFailuresCountingHandler;
import com.github.mkopylec.recaptcha.security.login.LoginFailuresManager;
import com.github.mkopylec.recaptcha.security.login.RecaptchaAwareRedirectStrategy;
import com.github.mkopylec.recaptcha.validation.RecaptchaValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

@Configuration("recaptchaSecurityConfiguration")
@ConditionalOnClass({EnableWebSecurity.class, AbstractAuthenticationProcessingFilter.class})
@ComponentScan({"com.github.mkopylec.recaptcha"})
public class SecurityConfiguration {

    private final RecaptchaProperties recaptcha;

    @Autowired
    public SecurityConfiguration(RecaptchaProperties recaptcha) {
        this.recaptcha = recaptcha;
    }

    @Bean
    @ConditionalOnMissingBean
    public FormLoginConfigurerEnhancer formLoginConfigurerEnhancer(LoginFailuresClearingHandler successHandler, LoginFailuresCountingHandler failureHandler, RecaptchaValidator userResponseValidator, LoginFailuresManager failuresManager) {
        RecaptchaAuthenticationFilter authenticationFilter = new RecaptchaAuthenticationFilter(userResponseValidator, recaptcha, failuresManager);
        return new FormLoginConfigurerEnhancer(authenticationFilter, successHandler, failureHandler);
    }

    @Bean
    @ConditionalOnMissingBean
    public LoginFailuresCountingHandler loginFailuresCountingHandler(LoginFailuresManager failuresManager, RecaptchaAwareRedirectStrategy redirectStrategy) {
        return new LoginFailuresCountingHandler(failuresManager, recaptcha, redirectStrategy);
    }

    @Bean
    @ConditionalOnMissingBean
    public LoginFailuresClearingHandler loginFailuresClearingHandler(LoginFailuresManager failuresManager) {
        return new LoginFailuresClearingHandler(failuresManager);
    }

    @Bean
    @ConditionalOnMissingBean
    public RecaptchaAwareRedirectStrategy recaptchaAwareRedirectStrategy(LoginFailuresManager failuresManager) {
        return new RecaptchaAwareRedirectStrategy(failuresManager);
    }
    
}
