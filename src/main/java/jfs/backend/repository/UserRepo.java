package jfs.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jfs.backend.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	Optional<User> findByEmail(String email);
	
}
