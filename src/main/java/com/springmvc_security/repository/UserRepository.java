package com.springmvc_security.repository;

import com.springmvc_security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmailAndUsername(String email, String username);

    default List<User> findUserBy(UserSpecification userSpecification) {
        return findAll(userSpecification);
    }

    boolean existsUserByEmail(String email);

    boolean existsUserByUsername(String username);
}
