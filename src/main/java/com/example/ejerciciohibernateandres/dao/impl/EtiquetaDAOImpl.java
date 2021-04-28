package com.example.ejerciciohibernateandres.dao.impl;

import com.example.ejerciciohibernateandres.dao.EtiquetaDAO;
import com.example.ejerciciohibernateandres.model.Etiqueta;
import com.example.ejerciciohibernateandres.service.EtiquetaService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EtiquetaDAOImpl implements EtiquetaDAO {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private Session session;

    private final EtiquetaService etiquetaService;

    public EtiquetaDAOImpl(EtiquetaService etiquetaService) {
        this.etiquetaService = etiquetaService;
    }

    @Override
    public List<Etiqueta> encontrarPorNombre(String nombre) {


        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Etiqueta> criteria = builder.createQuery(Etiqueta.class);
        Root<Etiqueta> root = criteria.from(Etiqueta.class);

        criteria.select(root).where(builder.like(root.get("nombre"), nombre+"%"));

        // ESTO ES PORQUE , DESPUES DE ACTUALIZAR UNA ETIQUETA, AL REPETIR 'ENCONTRAR TODAS'
        // DEVOLVÍA LOS VALORES SIN ACTUALIZAR.
        List<Etiqueta> listaResultado = new ArrayList<>();
        for (Etiqueta etiqueta :
                session.createQuery(criteria).getResultList()) {
            listaResultado.add(etiquetaService.encontrarEtiqueta(etiqueta.getId()).get());
        }


        return listaResultado;


    }
}
