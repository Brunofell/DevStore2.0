package com.example.DevStore.repository;

import com.example.DevStore.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findAllByAtivoTrue();

    UserDetails findByEmail(String email); // ou findByLogin ?????

    boolean existsByEmail(String email);
}
