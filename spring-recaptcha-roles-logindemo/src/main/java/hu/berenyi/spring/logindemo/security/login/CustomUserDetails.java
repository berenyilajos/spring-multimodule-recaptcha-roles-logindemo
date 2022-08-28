package hu.berenyi.spring.logindemo.security.login;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import hu.berenyi.spring.logindemo.dto.RoleDTO;
import hu.berenyi.spring.logindemo.helper.DTOHelper;
import hu.berenyi.spring.logindemo.model.User;

public class CustomUserDetails implements UserDetails {
	private static final long serialVersionUID = 6236511082618172622L;
	private User user;
	private List<RoleDTO> roles;

	public CustomUserDetails(User user) {
		this.user = user;
		roles = DTOHelper.toDTO(user.getRoles());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@DateTimeFormat(pattern = "yyyy.MM.dd HH:mm")
	public Date getLastLoginTime() {
		return user.getLastLoginTime();
	}

	public String getFullName() {
		return user.getFirstName() + " " + user.getLastName();
	}

	public User getUser() {
		return user;
	}

}
