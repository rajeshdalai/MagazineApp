package com.example.springmvc.dao;

import com.example.springmvc.model.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Rajesh on 13-11-2016.
 */

@Repository
public class MailDaoImpl implements MailDao {

    @Autowired
    private HibernateTemplate template;

    public List<Mail> findAll() {
        String hql = "From Mail as m ORDER BY m.id";
        return (List<Mail>)template.find(hql);
    }

    public Mail findById(int id) {
        return template.get(Mail.class,id);
    }

    public boolean save(Mail mail) {
        if(!mailExists(mail.getId())) {
            template.save(mail);
            return true;
        }else{
            return false;
        }
    }

    public void delete(int id) {
        template.delete(findById(id));
    }

    public void update(Mail mail) {
        Mail m = findById(mail.getId());
        m.setStatus(mail.getStatus());
        template.update(m);
    }

    public boolean mailExists(int id) {
        Mail m = findById(id);
        return m != null;
    }

}
