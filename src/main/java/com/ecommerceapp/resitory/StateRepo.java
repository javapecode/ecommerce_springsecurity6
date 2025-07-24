package com.ecommerceapp.resitory;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerceapp.entity.State;

public interface StateRepo extends JpaRepository<State,Integer> {
	  
	
		List<State> findByNameContainingIgnoreCase(String name);

}
