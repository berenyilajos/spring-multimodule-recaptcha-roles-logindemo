package hu.berenyi.spring.logindemo.security.login.manager;

import java.util.Calendar;
import java.util.Date;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import hu.berenyi.spring.logindemo.model.User;
import com.github.mkopylec.recaptcha.RecaptchaProperties;
import com.github.mkopylec.recaptcha.security.login.LoginFailuresManager;
import hu.berenyi.spring.logindemo.security.login.CustomUserDetails;
import hu.berenyi.spring.logindemo.services.LoginService;
import hu.berenyi.spring.logindemo.util.DateUtil;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomLoginFailuresManager extends LoginFailuresManager  {
	
	@Autowired
	private LoginService loginService;

	@Autowired
	public CustomLoginFailuresManager(RecaptchaProperties recaptcha) {
		super(recaptcha);
	}

	@Override
	public void addLoginFailure(HttpServletRequest request) {
		String username = getUsername(request);
        log.debug("Adding login failure for username: {}", username);
        loginService.increaseFailedAttempts(username);
	}

	@Override
	public int getLoginFailuresCount(HttpServletRequest request) {
		String username = getUsername(request);
		User user = loginService.getByUsername(username);
		if (user != null) {
	        int count = user.getFailedAttempts();
	        log.debug("Getting login failures count: {} for username: {}", count, username);
	        return count;
		} else {
			log.debug("There is no User in the db with username: {}", username);
			return 0;
		}
	}

	@Override
	public void clearLoginFailures(Authentication authentication) {
		Date now = Calendar.getInstance(DateUtil.LOCAL_HUNGARIAN_TIME_ZONE, DateUtil.LOCALE_HUNGARIAN).getTime();
		CustomUserDetails userDetails =  (CustomUserDetails) authentication.getPrincipal();
		String username = userDetails.getUsername();
        log.debug("Clearing login failures for username: {}", username);
        loginService.resetFailedAttempts(username, now);
    	userDetails.getUser().setFailedAttempts(0);
    	userDetails.getUser().setLastLoginTime(now);
	}

}
