package com.teckarch.TafDatastoreService.Service;

import com.teckarch.TafDatastoreService.Models.Users;
import com.teckarch.TafDatastoreService.Repository.UserRespository;
import com.teckarch.TafDatastoreService.Service.Interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRespository userRepository;

    @Override
    // Create a new user
    public Users createUser(Users user) {
        return userRepository.save(user);
    }

    @Override
    // Get all users
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    // Get a user by ID
    public Users getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);  // Return null if not found
    }

    @Override
    // Update a user by ID
    public Users updateUser(Long userId, Users updatedUser) {
        Users existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser != null) {
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setPhone(updatedUser.getPhone());
            return userRepository.save(existingUser);
        }
        return null;  // Return null if user does not exist
    }

    @Override
    // Delete a user by ID
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
