package com.springmvc_security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserResponseDto {
    private final Long user_id;
    private final String username;
    private final String email;
}
