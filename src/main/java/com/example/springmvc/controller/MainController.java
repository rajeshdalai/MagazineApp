package com.example.springmvc.controller;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.springmvc.model.Mail;
import com.example.springmvc.service.MailService;
 
@RestController
public class MainController {
 
    @Autowired
    MailService mailService;  //Service which will do all data retrieval/manipulation work
 
    
    //-------------------Retrieve All Mails--------------------------------------------------------
     
    @RequestMapping(value = "/mail/", method = RequestMethod.GET)
    public ResponseEntity<List<Mail>> listAllMails() {
        List<Mail> mails = mailService.findAll();
        if(mails.isEmpty()){
            return new ResponseEntity<List<Mail>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Mail>>(mails, HttpStatus.OK);
    }
 
 
    
    //-------------------Retrieve Single Mail--------------------------------------------------------
     
    @RequestMapping(value = "/mail/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mail> getMail(@PathVariable("id") int id) {
        System.out.println("Fetching Mail with id " + id);
        Mail mail = mailService.findById(id);
        if (mail == null) {
            return new ResponseEntity<Mail>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Mail>(mail, HttpStatus.OK);
    }
 
     
     
    //-------------------Create a Mail--------------------------------------------------------
     
    @RequestMapping(value = "/mail/", method = RequestMethod.POST)
    public ResponseEntity<Void> createMail(@RequestBody Mail mail,    UriComponentsBuilder ucBuilder) {
        if (!mailService.save(mail)){
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }else {
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/mail/{id}").buildAndExpand(mail.getId()).toUri());
            return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
        }
    }
 
    
     
    //------------------- Update a Mail --------------------------------------------------------
     
    @RequestMapping(value = "/mail/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Mail> updateMail(@PathVariable("id") int id, @RequestBody Mail mail) {
        System.out.println("Updating Mail " + id);
         
        Mail currentMail = mailService.findById(id);
         
        if (currentMail==null) {
            System.out.println("Mail with id " + id + " not found");
            return new ResponseEntity<Mail>(HttpStatus.NOT_FOUND);
        }
 
        currentMail.setStatus(mail.getStatus());
        mailService.update(currentMail);
        return new ResponseEntity<Mail>(currentMail, HttpStatus.OK);
    }
 
    
    
    //------------------- Delete a Mail --------------------------------------------------------
     
    @RequestMapping(value = "/mail/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Mail> deleteMail(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting Mail with id " + id);
 
        Mail mail = mailService.findById(id);
        if (mail == null) {
            System.out.println("Unable to delete. Mail with id " + id + " not found");
            return new ResponseEntity<Mail>(HttpStatus.NOT_FOUND);
        }
 
        mailService.delete(id);
        return new ResponseEntity<Mail>(HttpStatus.NO_CONTENT);
    }

}