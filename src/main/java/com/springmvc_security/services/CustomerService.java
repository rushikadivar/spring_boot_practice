package com.springmvc_security.services;

import com.springmvc_security.config.CustomerDetails;
import com.springmvc_security.entity.Customer;
import com.springmvc_security.repository.CustomerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements UserDetailsService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String customerName) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByCustomerName(customerName).orElseThrow(() -> new RuntimeException(""));
        return new CustomerDetails(customer);
    }
}
