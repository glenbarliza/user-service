package com.microservices.user.services;

import com.microservices.user.dto.UserRegistrationDto;
import com.microservices.user.entities.Usuario;
import com.microservices.user.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Service layer for user business logic
@Service
@RequiredArgsConstructor
public class UserService {

    private final UsuarioRepository usuarioRepository;

    // Find user by ID
    public Optional<Usuario> getById(Long id) {
        return usuarioRepository.findById(id);
    }

    // Find user by email
    public Optional<Usuario> getByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    // List all users
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    // Save or update user
    public Usuario save(Usuario user) {
        return usuarioRepository.save(user);
    }

    public Usuario createFromAuth(UserRegistrationDto dto) {
        Usuario user = Usuario.builder()
                .name(dto.name())
                .email(dto.email())
                .phone(dto.phone())
                .department(dto.department())
                .build();
        return usuarioRepository.save(user);
    }

}
