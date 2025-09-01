package com.springmvc_security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserPatchRequestDto {
    @Size(min = 2, max = 100)
    private String username;

    @Size(min = 2, max = 250)
    private String password;

    public void setUsername(String username) {
        this.username = username.toLowerCase();
    }
}
