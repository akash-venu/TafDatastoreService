package com.teckarch.TafDatastoreService.Service.Interfaces;

import com.teckarch.TafDatastoreService.Models.Users;

import java.util.List;

public interface UserService {
    Users createUser(Users user);
    List<Users> getAllUsers();
    Users getUserById(Long userId);
    Users updateUser(Long userId, Users updatedUser);
    void deleteUser(Long userId);

}
