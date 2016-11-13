package com.example.springmvc.service;

import com.example.springmvc.dao.MailDao;
import com.example.springmvc.model.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Rajesh on 13-11-2016.
 */
@Service
@Transactional
public class MailServiceImpl implements MailService {

    @Autowired
    private MailDao mailDao;

    public List<Mail> findAll() {
        return mailDao.findAll();
    }

    public Mail findById(int id) {
        return mailDao.findById(id);
    }

    public boolean save(Mail mail) {
        if(!mailExists(mail.getId())) {
            mailDao.save(mail);
            return true;
        }
        else
            return false;
    }

    public void delete(int id) {
        mailDao.delete(id);
    }

    public void update(Mail mail) {
        mailDao.update(mail);
    }

    public boolean mailExists(int id) {
        return mailDao.mailExists(id);
    }

}
