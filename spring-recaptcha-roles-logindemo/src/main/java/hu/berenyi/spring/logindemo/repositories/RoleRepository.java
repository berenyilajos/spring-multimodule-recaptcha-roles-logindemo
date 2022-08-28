package hu.berenyi.spring.logindemo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.berenyi.spring.logindemo.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	public Optional<Role> findByName(String name);
}
