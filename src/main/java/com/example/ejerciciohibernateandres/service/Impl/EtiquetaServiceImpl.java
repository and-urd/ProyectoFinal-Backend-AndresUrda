package com.example.ejerciciohibernateandres.service.Impl;

import com.example.ejerciciohibernateandres.dao.impl.ExpertoDAOImpl;
import com.example.ejerciciohibernateandres.model.Etiqueta;
import com.example.ejerciciohibernateandres.model.Experto;
import com.example.ejerciciohibernateandres.repository.EtiquetaRepository;
import com.example.ejerciciohibernateandres.service.EtiquetaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EtiquetaServiceImpl implements EtiquetaService {

    private final Logger log = LoggerFactory.getLogger (EtiquetaServiceImpl.class);


    private final ExpertoDAOImpl expertoDAO;

    private final EtiquetaRepository etiquetaRepository;
    public EtiquetaServiceImpl(ExpertoDAOImpl expertoDAO, EtiquetaRepository etiquetaRepository) {
        this.expertoDAO = expertoDAO;
        this.etiquetaRepository = etiquetaRepository;
    }

    // Crear una etiqueta
    @Override
    public Etiqueta crearEtiqueta(Etiqueta etiqueta) {
        if(etiqueta.getId() == 0){

            etiqueta.setCreatedAt(LocalDate.now());
            etiqueta.setUpdatedAt(LocalDate.now());
            return etiquetaRepository.save(etiqueta);
        }else{
            log.error("ERROR creación de Etiqueta -> el `id` debe ser 0, pero su valor es id={}",etiqueta.getId());
            return null;
        }
    }

    // Crear una lista de etiquetas
    @Override
    public List<Etiqueta> crearEtiquetas(List<Etiqueta> etiquetas) {
        List<Etiqueta> resultado = null;

        if(etiquetas != null && !etiquetas.isEmpty()){
            for(Etiqueta etiqueta: etiquetas){
                if(etiqueta.getId() != null)
                    return new ArrayList<>();
            }
            resultado = etiquetaRepository.saveAll(etiquetas);
        }
        return resultado;
    }

    // Actualizar una etiqueta
    @Override
    public Etiqueta actualizarEtiqueta(Etiqueta etiqueta) {
        if(etiqueta.getId() != null && etiquetaRepository.existsById(etiqueta.getId())){
            etiqueta.setUpdatedAt(LocalDate.now());
            return etiquetaRepository.save(etiqueta);
        }else{
            log.error("ERROR actualizando Etiqueta: Puede que el id sea NULO, o que la etiqueta id={} no exista en BBDD", etiqueta.getId());
            return null;
        }
    }

    // Encontrar todas las etiquetas
    @Override
    public List<Etiqueta> encontrarTodas() {
        return etiquetaRepository.findAll();
    }

    // Encontrar todas con paginado
    @Override
    public Page<Etiqueta> encontrarTodas(Pageable pageable) {
        return etiquetaRepository.findAll(pageable);
    }

    // Encontrar etiqueta por su id
    @Override
    public Optional<Etiqueta> encontrarEtiqueta(Long id) {
        return etiquetaRepository.findById(id);
    }

    // Borra etiqueta por su id
    @Override
    public Boolean borrarEtiqueta(Long id) {
        if(etiquetaRepository.existsById(id)){

            // Lista de los expertos con esa etiqueta
            List<Experto> lista = expertoDAO.encontrarConFiltros("", id, "", "");

            for (Experto experto : lista) {
                // Borramos la etiqueta de cada uno de los expertos
                expertoDAO.borrarEtiquetaDeExperto(experto, id);
            }


            try{
                etiquetaRepository.deleteById(id);
            }catch (Exception ex){
                log.error("ERROR en el borrado de la etiqueta con id={}", id);
                return false;
            }

            return true;
        }else{
            log.error("ERROR en el borrado: La etiqueta con id={} no existe en BBDD", id);
            return false;
        }
    }
}
