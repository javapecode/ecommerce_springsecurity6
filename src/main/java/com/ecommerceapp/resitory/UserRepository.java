package com.ecommerceapp.resitory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerceapp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	// Name ke basis pe search
	List<User> findByNameContainingIgnoreCase(String name);

	// Email ke basis pe search
	List<User> findByEmailContainingIgnoreCase(String email);

	// Ya dono (name OR email) ke basis pe search
	List<User> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String name, String email);
	
	Optional<User> findByEmail(String email);

}