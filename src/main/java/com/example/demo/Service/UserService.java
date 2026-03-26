package com.example.demo.Service;

import com.example.demo.DTO.UserRequest;
import com.example.demo.DTO.UserResponse;
import com.example.demo.Repositories.UserRepository;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest request);
    List<UserResponse> getAllUsers();
    UserResponse getUserId();
}
