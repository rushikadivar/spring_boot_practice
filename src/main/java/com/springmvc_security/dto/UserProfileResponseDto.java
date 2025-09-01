package com.springmvc_security.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserProfileResponseDto {
    private final Long userProfileId;
    private final String fullName;
    private final String photo;
    @JsonIgnore
    private final UserResponseDto user;
}
