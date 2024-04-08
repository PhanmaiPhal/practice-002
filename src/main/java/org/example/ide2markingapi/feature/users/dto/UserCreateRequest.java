package org.example.ide2markingapi.feature.users.dto;


import jakarta.validation.constraints.*;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;

public record UserCreateRequest(
    @NotNull
    @Max(8000)
    @Positive
    Integer pin,

    @NotBlank
    @Size(max = 20)
    String phoneNumber,

    @NotBlank
    String password,

    @NotBlank
    String confirmedPassword,

    @NotBlank
    @Size(max = 50)
    String name,

    @NotBlank
    @Size(max = 20)
    String nationalCardId,

    @NotBlank
    @Size(max = 8)
    String gender,

    @NotBlank
    @Size(max = 20)
    String studentIdCard,

    @NotNull
    LocalDate dob,

    @NotEmpty
    List<RoleRequest> roles

){

}
