package com.example.springmvc.service;

import com.example.springmvc.model.Mail;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Rajesh on 13-11-2016.
 */

public interface MailService {

    List<Mail> findAll();

    Mail findById(int id);

    boolean save(Mail mail);

    void delete(int id);

    void update(Mail mail);

    boolean mailExists(int id);

}
