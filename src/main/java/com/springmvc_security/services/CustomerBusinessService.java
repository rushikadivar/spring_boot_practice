package com.springmvc_security.services;

import com.springmvc_security.entity.Customer;
import com.springmvc_security.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerBusinessService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer registerCustomer(Customer request) {
        return customerRepository.save(request);
    }


    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<Customer> getAllCustomer() {
        return customerRepository.findAll().stream().toList();
    }
}
