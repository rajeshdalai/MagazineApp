package com.example.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Rajesh on 13-11-2016.
 */

@Controller
public class TemplateController {

    @RequestMapping(value = "/templates/home")
    public String getHomeTemplate() {
        return "/templates/home";
    }

    @RequestMapping(value = "/templates/newmail")
    public String getNewmailTemplate() {
        return "/templates/newmail";
    }

    @RequestMapping(value = "/templates/editmail")
    public String getEditmailTemplate() {
        return "/templates/editmail";
    }

    @RequestMapping(value = "templates/dirPagination")
    public String getPagination() {
        return "templates/dirPagination";
    }
}
