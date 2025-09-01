package com.springmvc_security.controller;

import com.springmvc_security.dto.UserPatchRequestDto;
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

    @GetMapping("/all")
    public ResponseEntity<List<?>> getAllUsers() {
        return new ResponseEntity<>(this.userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable(name = "userId") @Positive(message = "id should be positive") Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> saveUser(@RequestBody() @Valid UserRequestDto user) {
        return new ResponseEntity<>(this.userService.save(user), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<?> getUserBy(@ModelAttribute @Valid UserSearchRequest userSearchRequest) {
        return new ResponseEntity<>(this.userService.getUserBy(userSearchRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@Positive(message = "id should be positive") @PathVariable("userId") Long id) {
        this.userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<?> updateUserPartial(@Positive(message = "id should be positive") @PathVariable(name = "userId") Long id, @RequestBody @Valid UserPatchRequestDto user) {
        return new ResponseEntity<>(this.userService.updateUserPartialById(id, user), HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@Positive(message = "id should be positive") @PathVariable(name = "userId") Long id, @RequestBody @Valid UserRequestDto user) {
        return new ResponseEntity<>(this.userService.updateUserById(id, user), HttpStatus.OK);
    }
}
