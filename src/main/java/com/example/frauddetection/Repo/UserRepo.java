package com.example.frauddetection.Repo;

import com.example.frauddetection.Entity.UserModel;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserModel, String> {
    boolean existsByUsername(@NotBlank(message = "email is required") String username);

    Optional<UserModel> findByUsername(@NotBlank(message = "username is requires") String username);
}
