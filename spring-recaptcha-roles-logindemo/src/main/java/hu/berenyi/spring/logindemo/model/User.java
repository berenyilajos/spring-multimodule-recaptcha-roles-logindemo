package hu.berenyi.spring.logindemo.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NonNull
	@Column(nullable = false, unique = true, length = 45)
	private String username;
	
	@NonNull
	@Column(nullable = false, length = 64)
	private String password;
	
	@NonNull
	@Column(nullable = false, length = 50)
	private String firstName;
	
	@NonNull
	@Column(nullable = false, length = 50)
	private String lastName;
	
	@Column(name = "failed_attempts")
    private int failedAttempts;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_login_time")
	private Date lastLoginTime;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
	  name = "user_role", 
	  joinColumns = @JoinColumn(name = "user_id"), 
	  inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	

}
