package com.example.ejerciciohibernateandres.repository;

import com.example.ejerciciohibernateandres.model.Etiqueta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtiquetaRepository extends JpaRepository<Etiqueta, Long> {
}
