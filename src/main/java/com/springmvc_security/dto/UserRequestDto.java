package com.springmvc_security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserRequestDto {
    @NotBlank
    @Size(min = 2, max = 100)
    private String username;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be blank")
    @Size(min = 2, max = 100)
    private String email;

    @Setter
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 2, max = 250)
    private String password;

    public void setUsername(String username) {
        this.username = username.toLowerCase();
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }
}
