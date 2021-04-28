package com.example.ejerciciohibernateandres.service.Impl;

import com.example.ejerciciohibernateandres.model.Etiqueta;
import com.example.ejerciciohibernateandres.model.Experto;
import com.example.ejerciciohibernateandres.repository.ExpertoRepository;
import com.example.ejerciciohibernateandres.service.EtiquetaService;
import com.example.ejerciciohibernateandres.service.ExpertoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class ExpertoServiceImpl implements ExpertoService {

    private final Logger log = LoggerFactory.getLogger (ExpertoServiceImpl.class);

    // Inyecto el repositorio de Experto y el servicio de Etiqueta
    private final ExpertoRepository expertoRepository;
    private final EtiquetaService etiquetaService;
    public ExpertoServiceImpl(ExpertoRepository expertoRepository, EtiquetaService etiquetaService) {
        this.expertoRepository = expertoRepository;
        this.etiquetaService = etiquetaService;
    }


    // Crea un experto
    @Override
    public Experto crearExperto(Experto experto) {
        Experto expertoNuevo = null;

        // Si alguna de las etiquetas NO existe en la BBDD, devuelve Null
        for (Etiqueta etiqueta :
                experto.getEtiquetas()) {
            if(!etiquetaService.encontrarEtiqueta(etiqueta.getId()).isPresent()){
                log.error("ERROR en Creación de Experto -> Etiqueta {}, no existe en BBDD",etiqueta.getId());
                return null;
            }
        }

        // Si hay etiquetas repetidas en el Experto
        if(etiquetasRepetidas(experto.getEtiquetas()) > 0){
            log.error("ERROR en Creación de Experto -> Etiquetas repetidas");
            return null;
        }

        if(experto.getId() == 0){
            experto.setCreatedAt(LocalDate.now());
            experto.setUpdatedAt(LocalDate.now());
            return expertoRepository.save(experto);
        }else {
            log.error("ERROR en Creación de Experto -> el Id debe ser nulo");
            return null;
        }
    }

    // Actualizar un experto
    @Override
    public Experto actualizarExperto(Experto experto) {

        // Si alguna de las etiquetas NO existe en la BBDD, devuelve Null
        for (Etiqueta etiqueta :
                experto.getEtiquetas()) {
            if(!etiquetaService.encontrarEtiqueta(etiqueta.getId()).isPresent()){
                log.error("ERROR en Actualización de Experto -> Etiqueta {}, no existe en BBDD",etiqueta.getId());
                return null;
            }
        }

        // Si hay etiquetas repetidas en el Experto
        if(etiquetasRepetidas(experto.getEtiquetas()) > 0){
            log.error("ERROR en Creación de Experto -> Etiquetas repetidas");
            return null;
        }

        Experto expertoActualizado = null;
        if(experto.getId() != 0 && expertoRepository.existsById(experto.getId())){
            experto.setUpdatedAt(LocalDate.now());
            return expertoActualizado = expertoRepository.save(experto);
        }else{
            log.error("ERROR al actualizar Experto con id={}", experto.getId());
            if(experto.getId() == null) log.error("ERROR al actualizar Experto -> el id es nulo");
            if(!expertoRepository.existsById(experto.getId())) log.error("ERROR al actualizar Experto -> el id={} no existe", experto.getId());
            return null;
        }
    }

    // Recupero todos los expertos
    @Override
    public List<Experto> encontrarTodos() {
        return expertoRepository.findAll();
    }

    @Override
    public Page<Experto> encontrarTodos(Pageable pageable) {
        return expertoRepository.findAll(pageable);
    }

    // Recupero un experto por su Id
    @Override
    public Optional<Experto> encontrarExperto(Long id) {
        return expertoRepository.findById(id);
    }

    // Borrar experto por su Id
    @Override
    public Boolean borrarExperto(Long id) {
        if(expertoRepository.existsById(id)){
            expertoRepository.deleteById(id);
            return true;
        }else{
            log.error("ERROR en Borrado de Experto -> el experto con id:{} NO existe", id);
            return false;
        }
    }

    // Método que comprueba si todas las etiquetas son distintas
    // Devuelve 0 si son distintas,
    // y un integer si existen duplicados.
    private Integer etiquetasRepetidas(List<Etiqueta> listaEtiquetas){
        List<Long> listaIds = new ArrayList<>();
        // Paso al array los Ids de las etiquetas
        for (Etiqueta etiqueta :
                listaEtiquetas) {
            listaIds.add(etiqueta.getId());
        }
        HashSet hs = new HashSet();
        hs.addAll(listaIds);

        return (listaEtiquetas.size() - hs.size());
    }

}
