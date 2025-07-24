package com.ecommerceapp.controller;

import com.ecommerceapp.dto.AuthRequest;
import com.ecommerceapp.dto.AuthResponse;
import com.ecommerceapp.entity.User;
import com.ecommerceapp.servic.UserService;
import com.ecommerceapp.servicImpl.JwtUtil;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/users/login")
	public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
		// Step 1: Authenticate user
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

		// Step 2: Load UserDetails
		UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());

		// Step 3: Generate JWT token
		String token = jwtUtil.generateToken(userDetails);

		// Step 4: Return token
		return ResponseEntity.ok(new AuthResponse(token));
	}

	@PostMapping("/users/register")
	public ResponseEntity<User> create(@RequestBody User user) {
		logger.info("Creating user: {}", user);
		return ResponseEntity.ok(userService.createUser(user));
	}

	@GetMapping("/admin/users/{id}")
	public ResponseEntity<User> getById(@PathVariable Long id) {
		logger.info("Fetching user with id: {}", id);
		return ResponseEntity.ok(userService.getUserById(id));
	}

	@GetMapping("/admin/users")
	public ResponseEntity<List<User>> getAll() {
		logger.info("Fetching all users");
		return ResponseEntity.ok(userService.getAllUsers());
	}

	@GetMapping("/admin/users/search")
	public ResponseEntity<List<User>> search(@RequestParam String username) {
		logger.info("Searching users with username: {}", username);
		return ResponseEntity.ok(userService.searchUsersByUsername(username));
	}

	@PutMapping("/admin/users/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
		logger.info("Updating user with id: {} and data: {}", id, user);
		return ResponseEntity.ok(userService.updateUser(id, user));
	}

	@DeleteMapping("/admin/users/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		logger.info("Deleting user with id: {}", id);
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}

	@PatchMapping("/admin/users/status/{id}")
	public ResponseEntity<String> updateUserStatus(@PathVariable Long id, @RequestParam boolean active) {
	    userService.updateStatus(id, active);
	    return ResponseEntity.ok("Status updated successfully");
	}


}
