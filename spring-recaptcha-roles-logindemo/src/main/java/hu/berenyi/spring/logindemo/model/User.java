package hu.berenyi.spring.logindemo.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
