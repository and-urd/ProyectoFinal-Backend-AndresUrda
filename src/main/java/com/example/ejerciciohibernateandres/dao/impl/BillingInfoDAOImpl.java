//package com.example.ejerciciohibernateandres.dao.impl;
//
//import com.example.ejerciciohibernateandres.dao.BillingInfoDAO;
//import com.example.ejerciciohibernateandres.model.BillingInfo;
//import com.example.ejerciciohibernateandres.repository.BillingInfoRepository;
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
//public class BillingInfoDAOImpl implements BillingInfoDAO {
//
//    @PersistenceContext
//    private EntityManager manager;
//
//    @Autowired
//    private Session session;
//
//     @Autowired
//     private BillingInfoRepository repository;
//
//    @Override
//    public List<BillingInfo> findByProductName(String producto) {
//
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<BillingInfo> criteria = builder.createQuery(BillingInfo.class);
//        Root<BillingInfo> root = criteria.from(BillingInfo.class);
//        criteria.select(root);
//
//        criteria.where(builder.like(root.get("producto"),'%'+producto+'%'));
//        List<BillingInfo> billingInfos = session.createQuery(criteria).list();
//
//        return billingInfos;
//    }
//}
