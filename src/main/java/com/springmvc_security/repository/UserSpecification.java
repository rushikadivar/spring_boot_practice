package com.springmvc_security.repository;

import com.springmvc_security.dto.UserSearchRequest;
import com.springmvc_security.entity.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserSpecification implements Specification<User> {
    private final UserSearchRequest userSearchRequest;

    public UserSpecification(UserSearchRequest userSearchRequest) {
        this.userSearchRequest = userSearchRequest;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates;

        Predicate predicate = criteriaBuilder.conjunction();

        boolean isUsername = Objects.nonNull(userSearchRequest.getUsername());
        boolean isEmail = Objects.nonNull(userSearchRequest.getEmail());


        if (isUsername && isEmail) {
            // both are present - query would be AND
            predicate = criteriaBuilder.and(
                    criteriaBuilder.equal(root.get("username"), userSearchRequest.getUsername()),
                    criteriaBuilder.equal(root.get("email"), userSearchRequest.getEmail())
            );
        }

        else if (isUsername) {
            predicate = criteriaBuilder.equal(root.get("username"), userSearchRequest.getUsername());
        }
        else if (isEmail) {
            predicate = criteriaBuilder.equal(root.get("email"), userSearchRequest.getEmail());
        }
        return predicate;
//        if (userSearchRequest.getUsername() != null && !userSearchRequest.getUsername().isEmpty()) {
//            predicates.add(criteriaBuilder.equal(root.get("username"), userSearchRequest.getUsername()));
//        }
//
//        if (userSearchRequest.getEmail() != null && !userSearchRequest.getEmail().isEmpty()) {
//            predicates.add(criteriaBuilder.equal(root.get("email"), userSearchRequest.getEmail()));
//        }

        // Combine predicates with AND (default)
//        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
