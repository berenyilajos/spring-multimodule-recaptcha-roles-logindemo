package com.github.mkopylec.recaptcha.validation;

import com.github.mkopylec.recaptcha.RecaptchaProperties;
import com.github.mkopylec.recaptcha.RecaptchaProperties.Validation.Timeout;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration("recaptchaValidationConfiguration")
@EnableConfigurationProperties(RecaptchaProperties.class)
@ComponentScan({"com.github.mkopylec.recaptcha"})
public class ValidationConfiguration {

    private final RecaptchaProperties recaptcha;

    public ValidationConfiguration(RecaptchaProperties recaptcha) {
        this.recaptcha = recaptcha;
    }
    
    @Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
    public RecaptchaValidator userResponseValidator(final RestTemplateBuilder templateBuilder, IpAddressResolver ipAddressResolver) {
        return new DefaultRecaptchaValidator(createRestTemplate(templateBuilder), recaptcha, ipAddressResolver);
    }

    @Bean
    public IpAddressResolver ipAddressResolver() {
        return new IpAddressResolver();
    }

    protected RestTemplate createRestTemplate(final RestTemplateBuilder templateBuilder) {
    	Timeout timeout = recaptcha.getValidation().getTimeout();
    	return templateBuilder
    	.setConnectTimeout(timeout.getConnect())
    	.setReadTimeout(timeout.getRead())
    	.build();
    }

    protected int toMilliseconds(Duration duration) {
        return (int) duration.toMillis();
    }
}
