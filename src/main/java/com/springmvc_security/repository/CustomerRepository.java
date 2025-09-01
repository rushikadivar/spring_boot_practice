package com.springmvc_security.repository;

import com.springmvc_security.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByCustomerName(String customerName);
}