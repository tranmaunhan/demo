package com.example.demo.Service.impl;

import com.example.demo.DTO.UserRequest;
import com.example.demo.DTO.UserResponse;
import com.example.demo.Models.Profile;
import com.example.demo.Models.User;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Service.UserService;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private  final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository= userRepository;
    }

    @Override

    public UserResponse createUser(UserRequest request) {

      Profile profile =Profile.builder()
              .email(request.getEmail())
              .address(request.getAddress())
              .phone(request.getPhone())
              .build();
      User user = User.builder()
              .name(request.getName())
              .profile(profile)
              .build();
      User save = userRepository.save(user);
      return UserResponse.builder().build();
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.GetAllUserAndProfile();

        if (users.isEmpty()) {
            return List.of();
        }

        return users.stream()
                .map(user -> UserResponse.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getProfile().getEmail())
                        .address(user.getProfile().getAddress())
                        .phone(user.getProfile().getPhone())
                        .build())
                .toList(); // Java 16+
    }

    @Override
    public UserResponse getUserId() {
        return null;
    }
}
