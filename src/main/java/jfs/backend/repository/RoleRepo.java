package jfs.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jfs.backend.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

	
}
