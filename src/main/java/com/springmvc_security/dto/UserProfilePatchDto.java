package com.springmvc_security.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserProfilePatchDto {
//    @JsonIgnore
//    private Long userId; // use only for updating profile - for now we use path variable for useId so can be neglected

    @Size(min = 2, max = 250)
    private String fullName;

    @Size(min = 1)
    private String photo;

    public void setFullName(String fullName) {
        this.fullName = fullName.toLowerCase();
    }
}
