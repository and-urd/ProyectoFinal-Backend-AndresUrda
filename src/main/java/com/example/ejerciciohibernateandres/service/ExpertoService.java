package com.example.ejerciciohibernateandres.service;

import com.example.ejerciciohibernateandres.model.Experto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ExpertoService {

    // Crear
    Experto crearExperto(Experto experto);

    // Actualizar
    Experto actualizarExperto(Experto experto);

    // Encontrar todos
    List<Experto> encontrarTodos();

    // Encontrar todos los expertos paginados
    Page<Experto> encontrarTodos(Pageable pageable);

    // Encontrar un experto
    Optional<Experto> encontrarExperto(Long id);

    // Borrar un experto
    Boolean borrarExperto(Long id);
}
