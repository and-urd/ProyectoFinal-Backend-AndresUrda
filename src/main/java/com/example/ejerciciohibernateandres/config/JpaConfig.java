package com.example.ejerciciohibernateandres.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

@Configuration
public class JpaConfig {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public Session getSession(){
        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        return  session;
    }
}
