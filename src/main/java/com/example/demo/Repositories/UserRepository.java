package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Models.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, UUID> {
@Query("SELECT u FROM User u LEFT JOIN FETCH u.profile ")
    List<User>GetAllUserAndProfile();
}
