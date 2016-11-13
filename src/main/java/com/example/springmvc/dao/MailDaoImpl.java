package com.example.springmvc.dao;

import com.example.springmvc.model.Mail;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Rajesh on 13-11-2016.
 */

@Repository
public class MailDaoImpl implements MailDao {


    @Autowired
    SessionFactory sessionFactory;


    public List<Mail> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Mail> items = null;
        try {
            session.beginTransaction();
            items = (List<Mail>) session.createQuery("from Mail").list();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        return items;
    }

    public Mail findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Mail mail = null;
        try {
            session.beginTransaction();
            mail = (Mail) session.get(Mail.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        return mail;
    }

    public void save(Mail mail) {
        Session session = sessionFactory.getCurrentSession();
        try{
            session.beginTransaction();
            session.save(mail);
        }catch (HibernateException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
    }

    public void delete(int id){
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            Mail mail = (Mail) session.get(Mail.class, id);
            if (mail != null) {
                session.delete(mail);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
    }

    public void update(Mail mail) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(mail);
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
    }

    public boolean mailExists(int id) {
        Mail m = findById(id);
        return m != null;
    }

}
