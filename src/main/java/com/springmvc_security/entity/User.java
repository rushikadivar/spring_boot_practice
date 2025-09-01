package com.springmvc_security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_table"/*, uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_name", "user_email"}) // enforce uniqueness across multiple columns together e.g. compositeKey (user_name + user_email must be unique as a pair)
}*/)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Profile profile;
    
    @Column(name = "user_name", length = 100, nullable=false, unique=true)
    public String username;

    @Column(name="user_email", length = 100, nullable=false, unique=true, updatable = false)
    public String email;

    @Column(name = "user_password", length = 250, nullable=false)
    public String password;
}
