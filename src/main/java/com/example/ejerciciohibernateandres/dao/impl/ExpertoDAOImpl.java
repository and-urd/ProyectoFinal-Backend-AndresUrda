package com.example.ejerciciohibernateandres.dao.impl;

import com.example.ejerciciohibernateandres.dao.ExpertoDAO;
import com.example.ejerciciohibernateandres.model.Etiqueta;
import com.example.ejerciciohibernateandres.model.Experto;
import com.example.ejerciciohibernateandres.service.ExpertoService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpertoDAOImpl implements ExpertoDAO {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private Session session;

    private final ExpertoService expertoService;
    public ExpertoDAOImpl(@Lazy ExpertoService expertoService) { // @Lazy es para resolver al dependencia cíclica
        this.expertoService = expertoService;
    }

    @Override
    public List<Experto> encontrarConFiltros(String nombre, Long etiqueta, String modalidad, String estado) {

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Experto> criteria = builder.createQuery(Experto.class);
        Root<Experto> root = criteria.from(Experto.class);

//        criteria.select(root).where(builder.like(root.get("nombre"), nombre+"%"));
        Predicate contieneNombre = builder.like(root.get("nombre"), nombre+"%");
        Predicate contieneModalidad = builder.like(root.get("modalidad"), modalidad+"%");
        Predicate contieneEstado = builder.like(root.get("estado"), estado+"%");

        List<Experto> listaCriteria = session.createQuery(criteria.where(builder.and(contieneNombre, contieneModalidad, contieneEstado))).getResultList();

        // Filtramos de la ´listaCriteria´ los expertos que tengan la etiqueta  `etiqueta` que buscamos.
        List<Experto> listafiltroEtiqueta = new ArrayList<>();

        for (Experto experto : listaCriteria) {
            if(etiqueta == 0){
                listafiltroEtiqueta.add(experto);
            }else{
                for (Etiqueta et :
                        experto.getEtiquetas()) {
                    if (et.getId() == etiqueta) listafiltroEtiqueta.add(experto);
                }
            }
        }


        // ESTO ES PORQUE , no actualizar los valores en la RESPUESTA
        List<Experto> listaResultado = new ArrayList<>();
        for (Experto experto :
                listafiltroEtiqueta) {
            listaResultado.add(expertoService.encontrarExperto(experto.getId()).get());
        }

        return listaResultado;
    }

    @Override
    public void borrarEtiquetaDeExperto(Experto experto, Long id) {
        // Recupero el experto de la BBDD
        Optional<Experto> expertoBD = expertoService.encontrarExperto(experto.getId());

        List<Etiqueta> listaEtiquetas = expertoBD.get().getEtiquetas();

        listaEtiquetas.removeIf(s -> s.getId()==id);

        expertoBD.get().setEtiquetas(listaEtiquetas);

        expertoService.actualizarExperto(expertoBD.get());

    }


}
