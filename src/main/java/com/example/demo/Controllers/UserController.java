package com.example.demo.Controllers;

import com.example.demo.DTO.UserRequest;
import com.example.demo.DTO.UserResponse;
import com.example.demo.Service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
   private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public UserResponse create(@RequestBody UserRequest request){
        return  userService.createUser(request);
    }
    @GetMapping
    public List<UserResponse> GetAll(){
        return userService.getAllUsers();
    }
}


