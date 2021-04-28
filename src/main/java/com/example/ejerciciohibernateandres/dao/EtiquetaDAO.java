package com.example.ejerciciohibernateandres.dao;

import com.example.ejerciciohibernateandres.model.Etiqueta;

import java.util.List;

public interface EtiquetaDAO {

    List<Etiqueta> encontrarPorNombre(String nombre);
}
