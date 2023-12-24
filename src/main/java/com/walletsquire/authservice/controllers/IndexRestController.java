package com.walletsquire.authservice.controllers;

import com.walletsquire.authservice.entities.Customer;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class IndexRestController {

    @GetMapping(path = "/api/v1/user")
    public ResponseEntity<Customer> users() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Travis Beste");
        customer.setStatus("not authenticated");
        customer.setUsername(null);
        return new ResponseEntity<>(customer , HttpStatus.OK);
    }

    @RolesAllowed("API")
    @GetMapping(path = "/api/v1/customer")
    public ResponseEntity<Customer> customers(Principal principal) {

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Travis Beste");
        customer.setStatus("authenticated");
        customer.setUsername(principal.getName());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("roles : " + authentication.getAuthorities());

        boolean hasUserRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("USER"));
        System.out.println("role user : " + hasUserRole);
        hasUserRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("API"));
        System.out.println("role api  : " + hasUserRole);

        return new ResponseEntity<>(customer , HttpStatus.OK);

    }

}
