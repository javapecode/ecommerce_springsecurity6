package com.ecommerceapp.resitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerceapp.entity.Role;

public interface RoleRepo extends JpaRepository<Role,Integer> {
  
	
	
}
