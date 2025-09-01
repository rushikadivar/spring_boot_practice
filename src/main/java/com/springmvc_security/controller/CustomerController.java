package com.springmvc_security.controller;

import com.springmvc_security.entity.Customer;
import com.springmvc_security.services.CustomerBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerBusinessService customerBusinessService;

    @PostMapping("/register")
    public ResponseEntity<Customer> register(@RequestBody Customer request) {
        return ResponseEntity.ok(customerBusinessService.registerCustomer(request));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Customer> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(customerBusinessService.getCustomerById(id));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Customer>> getAllUsers() {
        return ResponseEntity.ok(customerBusinessService.getAllCustomer());
    }
}
