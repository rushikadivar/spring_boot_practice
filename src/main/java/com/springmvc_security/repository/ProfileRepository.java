package com.springmvc_security.repository;

import com.springmvc_security.dto.UserResponseDto;
import com.springmvc_security.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByUserId(Long userId);

    List<Profile> findAllByUserId(Long userId);
}
