package org.example.ide2markingapi.feature.users;

import jakarta.validation.constraints.*;
import lombok.NoArgsConstructor;

public record UserCreateRequest(
        @NotNull
        @Min(4)
        @Max(4)
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
        @Size(max = 40)
        String name,
        @NotBlank
        @Size(max = 6)
        String gender,
        @NotBlank
        @Size(max = 20)
        String nationalCardId,

        @NotBlank
        @Size(max = 20)
        String studentCardId
) {
}
