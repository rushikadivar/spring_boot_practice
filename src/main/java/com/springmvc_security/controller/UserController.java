package com.springmvc_security.controller;

import com.springmvc_security.dto.UserRequestDto;
import com.springmvc_security.dto.UserResponseDto;
import com.springmvc_security.dto.UserSearchRequest;
import com.springmvc_security.entity.Profile;
import com.springmvc_security.services.ProfileService;
import com.springmvc_security.services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProfileService profileService;

    @GetMapping("/all")
    public ResponseEntity<List<?>> getAllUsers() {
        return new ResponseEntity<>(this.userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable(name = "id") @Positive(message = "id should be positive") Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> saveUser(@RequestBody() @Valid UserRequestDto user) {
        this.userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<?> getUserBy(@ModelAttribute @Valid UserSearchRequest userSearchRequest) {
        return new ResponseEntity<>(this.userService.getUserBy(userSearchRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@Positive(message = "id should be positive") @PathVariable("id") Long id) {
        this.userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable() Long id, @RequestBody @Valid UserRequestDto user) {
        this.userService.updateUserById(id, user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/profile")
    public ResponseEntity<?> updateUserProfile(@PathVariable Long id, @RequestBody Profile profile) {
        return new ResponseEntity<>(profileService.updateUserProfile(id, profile), HttpStatus.CREATED);
    }
}
