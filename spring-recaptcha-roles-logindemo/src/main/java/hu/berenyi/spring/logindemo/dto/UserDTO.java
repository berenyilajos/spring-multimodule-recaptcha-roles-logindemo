package hu.berenyi.spring.logindemo.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {
	private static final long serialVersionUID = 278735362141266476L;
	
	private String username;
	
	private String firstName;
	
	private String lastName;
	
	private int failedAttempts;
	
	@DateTimeFormat(pattern = "yyyy.MM.dd HH:mm")
	private Date lastLoginTime;
	
	private List<RoleDTO> roles;
	
	public String getFullName() {
		return getFirstName() + " " + getLastName();
	}

}
