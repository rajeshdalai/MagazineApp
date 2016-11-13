package com.example.springmvc.dao;

import com.example.springmvc.model.Mail;

import java.util.List;

/**
 * Created by Rajesh on 13-11-2016.
 */
public interface MailDao {

    List<Mail> findAll();

    Mail findById(int id);

    void save(Mail mail);

    void delete(int id);

    void update(Mail mail);

    boolean mailExists(int id);

}
