package com.ecommerceapp.servicImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerceapp.entity.User;
import com.ecommerceapp.exception.ResourceNotFoundException;
import com.ecommerceapp.resitory.UserRepository;
import com.ecommerceapp.servic.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepo;

	@Override
	public User createUser(User user) {
		try {
			if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
				throw new IllegalArgumentException("Email must not be empty");
			}
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			return userRepo.save(user);
		} catch (DataIntegrityViolationException e) {
			logger.error("Data integrity error in createUser(): {}", e.getMostSpecificCause().getMessage());
			throw new RuntimeException("Error while creating user.");
		}
	}

	@Override
	public User getUserById(Long id) {
		return userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id,
				this.getClass().getSimpleName(), "getUserById"));
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	@Override
	public List<User> searchUsersByUsername(String username) {
		return userRepo.findByEmailContainingIgnoreCase(username);
	}

	@Override
	public User updateUser(Long id, User updatedUser) {
		User user = getUserById(id);
		user.setName(updatedUser.getName());
		user.setEmail(updatedUser.getEmail());
		user.setPassword(updatedUser.getPassword());
		user.setRoles(updatedUser.getRoles());
		return userRepo.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		User user = getUserById(id);
		userRepo.delete(user);
	}
	public void updateStatus(Long id, boolean active) {
	    User user = userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found...!","User","update status.."));
	    user.setActive(active);
	    userRepo.save(user);
	}
	
	
}
