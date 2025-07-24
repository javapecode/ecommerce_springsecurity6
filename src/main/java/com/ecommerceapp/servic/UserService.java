package com.ecommerceapp.servic;

import java.util.List;

import com.ecommerceapp.entity.User;

import com.ecommerceapp.entity.User;
import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    List<User> searchUsersByUsername(String username);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    public void updateStatus(Long id, boolean active);
}
