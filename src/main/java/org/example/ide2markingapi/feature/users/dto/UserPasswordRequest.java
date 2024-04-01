package org.example.ide2markingapi.feature.users.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserPasswordRequest(
        @NotBlank
        @Size(max = 40)
        String oldPassword,

        @NotBlank
        String NewPassword,

        @NotBlank
        String confirmedNewPassword
) {
}
