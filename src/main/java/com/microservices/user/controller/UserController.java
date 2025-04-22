package com.microservices.user.controller;

import com.microservices.user.dto.UserRegistrationDto;
import com.microservices.user.entities.Usuario;
import com.microservices.user.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

// REST controller for user-related operations
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Returns user by ID if found, or 404 otherwise
    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Optional<Usuario> user = userService.getById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/me")
    @Operation(summary = "Get info of authenticated user")
    public ResponseEntity<?> getMe(HttpServletRequest request) {
        String email = (String) request.getAttribute("userEmail"); // set by JwtAuthFilter

        if (email == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        return userService.getByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/internal/users")
    @Operation(summary = "Internal endpoint to create user from auth-service")
    public ResponseEntity<?> createFromAuth(@RequestBody UserRegistrationDto dto) {
        Usuario savedUser = userService.createFromAuth(dto);
        return ResponseEntity.ok(savedUser);
    }


}
