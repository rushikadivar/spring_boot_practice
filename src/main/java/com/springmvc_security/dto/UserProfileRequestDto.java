package com.springmvc_security.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserProfileRequestDto {
//    @NotBlank
//    private Long userId; // use only for adding profile - for now we use path variable for useId so can be neglected

    @NotBlank
    private String fullName;

    @NotBlank
    String photo;

    public void setFullName(String fullName) {
        this.fullName = fullName.toLowerCase();
    }
}
