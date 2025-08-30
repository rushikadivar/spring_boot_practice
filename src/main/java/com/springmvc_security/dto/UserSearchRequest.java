package com.springmvc_security.dto;

import com.springmvc_security.constraints.userrequestconstraints.AtLeastOneField;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@AtLeastOneField
public class UserSearchRequest {

    @Size(min = 2, max = 100)
    private String username;

    @Email(message = "Invalid email format")
    @Size(min = 2, max = 100)
    private String email;

    public void setUsername(String username) {
        this.username = username.toLowerCase().trim();
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase().trim();
    }
}
