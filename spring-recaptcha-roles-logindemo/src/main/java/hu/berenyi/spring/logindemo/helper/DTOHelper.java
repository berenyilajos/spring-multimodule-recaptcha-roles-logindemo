package hu.berenyi.spring.logindemo.helper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import hu.berenyi.spring.logindemo.dto.RoleDTO;
import hu.berenyi.spring.logindemo.dto.UserDTO;
import hu.berenyi.spring.logindemo.model.Role;
import hu.berenyi.spring.logindemo.model.User;

public class DTOHelper {
	
	public static RoleDTO toDTO(Role role) {
		return new RoleDTO(role.getName());
	}
	
	public static List<RoleDTO> toDTO(Set<Role> roles) {
		return roles.stream().map(r -> toDTO(r)).collect(Collectors.toList());
	}
	
	public static UserDTO toDTO(User user) {
		return new UserDTO(user.getUsername(), user.getFirstName(), user.getLastName(), user.getFailedAttempts(), user.getLastLoginTime(), toDTO(user.getRoles()));
	}
	
	public static List<UserDTO> toDTO(List<User> users) {
		return users.stream().map(u -> toDTO(u)).collect(Collectors.toList());
	}

}
