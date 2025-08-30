package com.springmvc_security.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "address_table")
public class Address {
    @Id
    private Long id;

    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;
}
