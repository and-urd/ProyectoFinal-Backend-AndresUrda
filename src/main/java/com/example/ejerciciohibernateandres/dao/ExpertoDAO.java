package com.example.ejerciciohibernateandres.dao;

import com.example.ejerciciohibernateandres.model.Experto;

import java.util.List;

public interface ExpertoDAO {

    List<Experto> encontrarConFiltros(String nombre, Long etiqueta, String modalidad, String estado);

    // Borra del experto pasado la etiqueta `id` si es que la tiene
    void borrarEtiquetaDeExperto(Experto experto, Long id);

}
