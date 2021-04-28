package com.example.ejerciciohibernateandres.repository;

import com.example.ejerciciohibernateandres.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
