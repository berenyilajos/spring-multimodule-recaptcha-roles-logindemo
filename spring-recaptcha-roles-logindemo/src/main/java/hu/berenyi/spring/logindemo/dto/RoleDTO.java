package hu.berenyi.spring.logindemo.dto;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class RoleDTO  implements GrantedAuthority {
	private static final long serialVersionUID = 2843077443636084625L;
	@NonNull
	private String name;
	
	@Override
	public String getAuthority() {
		return name;
	}

	@Override
	public String toString() {
		return getAuthority();
	}
	
}
