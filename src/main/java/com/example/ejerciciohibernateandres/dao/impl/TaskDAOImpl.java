//package com.example.ejerciciohibernateandres.dao.impl;
//
//import com.example.ejerciciohibernateandres.dao.TaskDAO;
//import com.example.ejerciciohibernateandres.model.Task;
//import org.hibernate.Session;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;
//import java.util.List;
//
//@Repository
//public class TaskDAOImpl implements TaskDAO {
//
//    @PersistenceContext
//    private EntityManager manager;
//
//    @Autowired
//    private Session session;
//
//    @Override
//    public List<Task> findAllTagsByTitulo(String titulo) {
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<Task> criteria =  builder.createQuery(Task.class);
//        Root<Task> root = criteria.from(Task.class);
//
//        criteria.select(root).where(builder.like(root.get("titulo"), '%' + titulo + '%' ));
//
//        return session.createQuery(criteria).getResultList();
//
//    }
//}
