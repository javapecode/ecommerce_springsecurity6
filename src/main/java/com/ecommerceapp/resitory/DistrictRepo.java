package com.ecommerceapp.resitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerceapp.entity.District;

public interface DistrictRepo extends JpaRepository<District,Integer> {
	  
	List<District> findByNameContainingIgnoreCase(String name);
		
}
