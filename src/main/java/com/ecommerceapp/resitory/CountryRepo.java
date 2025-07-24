package com.ecommerceapp.resitory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerceapp.entity.Country;

public interface CountryRepo extends JpaRepository<Country, Integer> {

	List<Country> findByNameContainingIgnoreCase(String name);

	List<Country> findByStatus(boolean status);

	List<Country> findByNameContainingIgnoreCaseAndStatus(String name, boolean status);

}
