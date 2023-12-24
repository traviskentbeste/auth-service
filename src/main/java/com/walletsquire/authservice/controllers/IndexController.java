package com.walletsquire.authservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "external";
    }

    @GetMapping(path = "/customers")
    public String customers(Principal principal, Model model) {
        //addCustomers();
        //model.addAttribute("customers", customerDAO.findAll());
        model.addAttribute("username", principal.getName());
        return "customers";
    }

}
