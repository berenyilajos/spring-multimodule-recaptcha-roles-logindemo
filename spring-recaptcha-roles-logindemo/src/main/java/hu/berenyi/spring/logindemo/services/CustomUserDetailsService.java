package hu.berenyi.spring.logindemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hu.berenyi.spring.logindemo.model.User;
import hu.berenyi.spring.logindemo.repositories.UserRepository;
import hu.berenyi.spring.logindemo.security.login.CustomUserDetails;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	
	static String USER_NOT_FOUND_MSG = "User not found";
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(USER_NOT_FOUND_MSG);
		}
		return new CustomUserDetails(user);
	}

}
