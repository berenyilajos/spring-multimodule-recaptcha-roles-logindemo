package hu.berenyi.spring.logindemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.mkopylec.recaptcha.security.SecurityConfiguration;
import com.github.mkopylec.recaptcha.validation.ValidationConfiguration;

import hu.berenyi.spring.logindemo.config.MvcConfiguration;

@SpringBootApplication
public class SpringRecaptchaRolesLogindemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(new Class<?>[]{SpringRecaptchaRolesLogindemoApplication.class, ValidationConfiguration.class, SecurityConfiguration.class, MvcConfiguration.class}, args);
	}

}
