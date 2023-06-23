package com.mongotask.api.model;


import com.mongotask.api.constant.RegexConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private String id;
    @NotBlank
    @Size(min = 2, max = 255)
    @NotBlank
    @NonNull
    private String firstName;
    @NotBlank
    @Size(min = 2, max = 255)
    @NotBlank
    @NonNull
    private String lastName;
    @NotBlank
    @Size(min = 5, max = 320)
    @NotBlank
    @NonNull
    @Pattern(regexp = RegexConstants.EMAIL)
    private String email;
}
