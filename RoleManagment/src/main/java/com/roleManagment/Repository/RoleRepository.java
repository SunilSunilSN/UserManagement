package com.roleManagment.Repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.roleManagment.Entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);
	//Set<Role> findAllById(Set<Long> roleId);
}
