//package com.example.ejerciciohibernateandres.dao.impl;
//
//import com.example.ejerciciohibernateandres.dao.TagDAO;
//import com.example.ejerciciohibernateandres.model.Tag;
//import com.example.ejerciciohibernateandres.model.TagColor;
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
//public class TagDAOImpl implements TagDAO {
//
//    @PersistenceContext
//    private EntityManager manager;
//
//    @Autowired
//    private Session session;
//
//    @Override
//    public List<Tag> findByColorTag(TagColor tagColor) {
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<Tag> criteria =  builder.createQuery(Tag.class);
//        Root<Tag> root = criteria.from(Tag.class);
//
//        criteria.select(root).where(builder.equal(root.get("color"), tagColor));
//
//
//        return session.createQuery(criteria).getResultList();
//
//    }
//}
