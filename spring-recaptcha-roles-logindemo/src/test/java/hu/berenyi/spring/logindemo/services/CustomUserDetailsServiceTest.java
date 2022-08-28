package hu.berenyi.spring.logindemo.services;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import hu.berenyi.spring.logindemo.model.User;
import hu.berenyi.spring.logindemo.repositories.UserRepository;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomUserDetailsServiceTest {
	
	@InjectMocks
	private CustomUserDetailsService underTest;

	@Mock
	private UserRepository userRepository;
	private static final String existingUserName = "existingUserName";
	private static final String notExistingUserName = "notExistingUserName";
	private static User existingUser;

	@Before
	public void setUp() throws Exception {
		existingUser = new User();
		existingUser.setUsername(existingUserName);
		when(userRepository.findByUsername(existingUserName)).thenReturn(existingUser);
		when(userRepository.findByUsername(notExistingUserName)).thenReturn(null);
	}

	@Test
	public void loadUserByUsernameTest() throws UsernameNotFoundException {
		UserDetails userDetails = underTest.loadUserByUsername(existingUserName);
		verify(userRepository, times(1)).findByUsername(existingUserName);
		
		assertEquals(existingUserName, userDetails.getUsername());

		Assertions.assertThatExceptionOfType(UsernameNotFoundException.class)
				.isThrownBy(() -> underTest.loadUserByUsername(notExistingUserName))
				.withMessage(CustomUserDetailsService.USER_NOT_FOUND_MSG);
	}


}
