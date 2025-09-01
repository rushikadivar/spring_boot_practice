package com.springmvc_security.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserResponseDto {
    private final Long userId;
    private final String username;
    private final String email;
    @JsonProperty("userProfile")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserProfileResponseDto userProfileResponseDto;
}
