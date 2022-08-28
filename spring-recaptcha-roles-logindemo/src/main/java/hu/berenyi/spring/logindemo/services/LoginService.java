package hu.berenyi.spring.logindemo.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.berenyi.spring.logindemo.model.User;
import hu.berenyi.spring.logindemo.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class LoginService {

	@Autowired
	private UserRepository userRepo;

	public User getByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	public void increaseFailedAttempts(String username) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			user.setFailedAttempts(user.getFailedAttempts() + 1);
			log.debug("Increased ailedAttempts to {} with username: {}", user.getFailedAttempts(), username);
		} else {
			log.debug("There is no User in the db with username: {}", username);
		}
	}

	public void increaseFailedAttempts(User user) {
		int newFailAttempts = user.getFailedAttempts() + 1;
		userRepo.updateFailedAttempts(newFailAttempts, user.getUsername());
	}

	public void resetFailedAttempts(String username, Date lastLoginTime) {
		userRepo.updateFailedAttemptsAndLastLoginTime(0, lastLoginTime, username);
	}
}
