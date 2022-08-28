package hu.berenyi.spring.logindemo.dataloader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import hu.berenyi.spring.logindemo.model.Role;
import hu.berenyi.spring.logindemo.model.User;
import hu.berenyi.spring.logindemo.repositories.RoleRepository;
import hu.berenyi.spring.logindemo.repositories.UserRepository;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
    public void run(ApplicationArguments args) {
    	Role adminRole = new Role("ROLE_Administrator");
    	Role signedInUserRole = new Role("ROLE_Signed in user");
    	Role contentEditorRole = new Role("ROLE_Content editor");
    	roleRepository.save(adminRole);
    	roleRepository.save(signedInUserRole);
    	roleRepository.save(contentEditorRole);
    	User user1 = new User("User1", passwordEncoder.encode("password1"), "User", "One");
    	user1.getRoles().add(contentEditorRole);
    	user1.getRoles().add(signedInUserRole);
        userRepository.save(user1);
        
        User user2 = new User("User2", passwordEncoder.encode("password2"), "User", "Two");
    	user2.getRoles().add(contentEditorRole);
        userRepository.save(user2);
        
        User user3 = new User("User3", passwordEncoder.encode("password3"), "User", "Three");
    	user3.getRoles().add(signedInUserRole);
        userRepository.save(user3);
        
        User admin = new User("Admin", passwordEncoder.encode("passwordA"), "Admin", "Admin");
        admin.getRoles().add(adminRole);
        userRepository.save(admin);
    }
}
