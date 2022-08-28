package hu.berenyi.spring.logindemo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.berenyi.spring.logindemo.model.User;
import hu.berenyi.spring.logindemo.repositories.UserRepository;

@Service
@Transactional
public class UserServices {

	@Autowired
	private UserRepository userRepo;

	public List<User> findAllUsers() {
		return userRepo.findAll();
	}

}
