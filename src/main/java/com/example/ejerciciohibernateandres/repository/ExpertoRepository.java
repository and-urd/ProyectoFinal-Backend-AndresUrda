package com.example.ejerciciohibernateandres.repository;

import com.example.ejerciciohibernateandres.model.Experto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpertoRepository extends JpaRepository<Experto, Long> {
}
