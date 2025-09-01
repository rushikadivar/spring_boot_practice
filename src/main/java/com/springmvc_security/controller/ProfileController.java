package com.springmvc_security.controller;

import com.springmvc_security.dto.UserProfilePatchDto;
import com.springmvc_security.dto.UserProfileRequestDto;
import com.springmvc_security.entity.Profile;
import com.springmvc_security.services.ProfileService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/profile")
@Validated
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserProfiles(@Positive @PathVariable("userId") Long userId) {
        return new ResponseEntity<>(profileService.getUserProfiles(userId), HttpStatus.OK);
    }

    @PutMapping("/{userId}/profile")
    public ResponseEntity<?> updateUserProfile(@Positive(message = "id should be positive") @PathVariable(name = "userId") Long id, @RequestBody UserProfileRequestDto profile) {
        return new ResponseEntity<>(profileService.updateUserProfile(id, profile), HttpStatus.OK);
    }

    @PatchMapping("/{userId}/profile")
    public ResponseEntity<?> patchUserProfile(@Positive(message = "id should be positive") @PathVariable(name = "userId") Long id, @RequestBody UserProfilePatchDto profile) {
        return new ResponseEntity<>(profileService.patchUserProfile(id, profile), HttpStatus.OK);
    }

    @PostMapping("/{userId}/profile")
    public ResponseEntity<?> addUserProfile(@Positive(message = "id should be positive") @PathVariable(name = "userId") Long id, @RequestBody UserProfileRequestDto profile) {
        return new ResponseEntity<>(profileService.addUserProfile(id, profile), HttpStatus.CREATED);
    }
}
