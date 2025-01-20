package com.teckarch.TafDatastoreService.Controllers;

//import org.springframework.web.bind.annotation.RestController;
//
//import com.teckarch.TafDatastoreService.Models.Users;
//import com.teckarch.TafDatastoreService.Service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/datastore/users")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping
//    public ResponseEntity<Users> createUser(@RequestBody Users user) {
//        Users createdUser = userService.createUser(user);
//        return ResponseEntity.ok(createdUser);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Users>> getAllUsers() {
//        return ResponseEntity.ok(userService.getAllUsers());
//    }
//
//    @GetMapping("/{userId}")
//    public ResponseEntity<Users> getUserById(@PathVariable Long userId) {
//        Optional<Users> user = userService.getUserById(userId);
//        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @PutMapping("/{userId}")
//    public ResponseEntity<Users> updateUser(@PathVariable Long userId, @RequestBody Users updatedUser) {
//        Users updated = userService.updateUser(userId, updatedUser);
//        return ResponseEntity.ok(updated);
//    }
//
//    @DeleteMapping("/{userId}")
//    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
//        userService.deleteUser(userId);
//        String message = "User with ID " + userId + " has been successfully deleted.";
//        return ResponseEntity.ok(message);
//    }
//}

import com.teckarch.TafDatastoreService.Models.Users;
import com.teckarch.TafDatastoreService.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/datastore/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    // Create a new user
    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        Users createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Get a user by ID
    @GetMapping("/{userId}")
    public ResponseEntity<Users> getUserById(@PathVariable Long userId) {
        Users user = userService.getUserById(userId);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    // Update a user by ID
    @PutMapping("/{userId}")
    public ResponseEntity<Users> updateUser(@PathVariable Long userId, @RequestBody Users updatedUser) {
        Users updated = userService.updateUser(userId, updatedUser);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        String message = "User with ID " + userId + " has been successfully deleted.";
        return ResponseEntity.ok(message);
    }
}
