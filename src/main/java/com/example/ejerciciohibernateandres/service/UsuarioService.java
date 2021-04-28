package com.example.ejerciciohibernateandres.service;

import com.example.ejerciciohibernateandres.model.Usuario;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UsuarioService {

    // Login
    Map<String, String> loginUsuario(Usuario usuario);

    // Registro Usuario
    Map<String, String> registroUsuario(Usuario usuario);

    // Crear Usuario
    Usuario crearUsuario(Usuario usuario);

    // Update
    Usuario actualizarUsuario(Usuario usuario);

    // Encontrar todos
    List<Usuario> encontrarTodos();

    // Encontrar uno
    Optional<Usuario> encontrarUsuario(Long id);

}
