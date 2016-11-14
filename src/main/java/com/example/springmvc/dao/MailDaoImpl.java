package com.example.springmvc.dao;

import com.example.springmvc.model.Mail;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
        List<Mail> items = session.createCriteria(Mail.class).list();
        return items;
    }

    public Mail findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Mail mail = (Mail)session.createCriteria(Mail.class).add(Restrictions.eq("id",id)).uniqueResult();
        return mail;
    }

    public void save(Mail mail) {
        Session session = sessionFactory.getCurrentSession();
        session.save(mail);
    }

    public void delete(int id){
        Session session = sessionFactory.getCurrentSession();
        session.delete(findById(id));
    }

    public void update(Mail mail) {
        Session session = sessionFactory.getCurrentSession();
        session.update(mail);
    }

    public boolean mailExists(int id) {
        Mail m = findById(id);
        return m != null;
    }

}
