package com.microservices.user.repositories;

import com.microservices.user.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Repository interface for accessing Usuario data
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
