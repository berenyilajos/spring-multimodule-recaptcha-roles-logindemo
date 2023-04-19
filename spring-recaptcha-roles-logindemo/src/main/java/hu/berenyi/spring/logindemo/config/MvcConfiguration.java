package hu.berenyi.spring.logindemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.github.mkopylec.recaptcha.security.login.FormLoginConfigurerEnhancer;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@ComponentScan({ "com.github.mkopylec.recaptcha", "hu.berenyi.spring.logindemo" })
public class MvcConfiguration {

	@Autowired
	private FormLoginConfigurerEnhancer enhancer;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder);

		return authProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		enhancer.addRecaptchaSupport(http.formLogin()).loginPage("/login")
				.and()
				.csrf().disable()
				.authorizeHttpRequests()
				.requestMatchers("/login**").permitAll()
				.requestMatchers("/admin/**").hasRole("Administrator")
				.requestMatchers("/content/**").hasAnyRole("Administrator", "Content editor")
				.requestMatchers("/signedin/**")
				.hasAnyRole("Administrator", "Signed in user")
				.requestMatchers("/**").authenticated().and()
				.exceptionHandling().accessDeniedPage("/accessdenied");
		http.authenticationProvider(authenticationProvider());

		return http.build();
	}
}
