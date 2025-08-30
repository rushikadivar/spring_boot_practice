package com.springmvc_security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_table", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_name", "user_email"})
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    private Profile profile;
    @Column(name = "user_name", nullable=false, length = 100, unique=true)
    public String username;

    @Column(name="user_email", nullable=false, unique=true, length = 100, updatable = false)
    public String email;

    @Column(name = "user_password", nullable=false, length = 250)
    public String password;
}
