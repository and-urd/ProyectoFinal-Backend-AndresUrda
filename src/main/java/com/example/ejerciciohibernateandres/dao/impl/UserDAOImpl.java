//package com.example.ejerciciohibernateandres.dao.impl;
//
//import com.example.ejerciciohibernateandres.dao.UserDAO;
//import com.example.ejerciciohibernateandres.model.User;
//import com.example.ejerciciohibernateandres.repository.UserRepository;
//import org.hibernate.Session;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;
//
//@Repository
//public class UserDAOImpl implements UserDAO {
//
//    @PersistenceContext
//    private EntityManager manager;
//
//    @Autowired
//    private Session session;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public User findByDni(String dni) {
//
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<User> criteria = builder.createQuery(User.class);
//        Root<User> root = criteria.from(User.class);
//        criteria.select(root);
//
//        criteria.where(builder.equal(root.get("dni"),dni));
//
//        User userDni = session.createQuery(criteria).getSingleResult();
//
//        return userDni;
//    }
//}
