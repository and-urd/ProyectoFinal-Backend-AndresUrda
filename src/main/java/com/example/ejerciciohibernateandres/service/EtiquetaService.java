package com.example.ejerciciohibernateandres.service;

import com.example.ejerciciohibernateandres.model.Etiqueta;
import net.bytebuddy.matcher.EqualityMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EtiquetaService {

    // Crear
    Etiqueta crearEtiqueta(Etiqueta etiqueta);

    // Crear lista de etiquetas
    List<Etiqueta> crearEtiquetas(List<Etiqueta> etiquetas);

    // Actualizar
    Etiqueta actualizarEtiqueta(Etiqueta etiqueta);

    // Encontrar todas las etiquetas
    List<Etiqueta> encontrarTodas();

    // Encontart todas las etiquetas paginadas
    Page<Etiqueta> encontrarTodas(Pageable pageable);

    // Encontrar una etiqueta
    Optional<Etiqueta> encontrarEtiqueta(Long id);

    // Borrar una etiqueta
    Boolean borrarEtiqueta(Long id);


}
