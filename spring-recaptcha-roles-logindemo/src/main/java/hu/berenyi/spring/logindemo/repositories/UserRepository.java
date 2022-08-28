package hu.berenyi.spring.logindemo.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import hu.berenyi.spring.logindemo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUsername(String username);

	@Query("UPDATE User u SET u.failedAttempts = ?1 WHERE u.username = ?2")
	@Modifying
	public void updateFailedAttempts(int failAttempts, String username);
	
	@Query("UPDATE User u SET u.failedAttempts = ?1, u.lastLoginTime = ?2 WHERE u.username = ?3")
	@Modifying
	public void updateFailedAttemptsAndLastLoginTime(int failAttempts, Date lastLoginTime, String username);

}
