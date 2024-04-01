package org.example.ide2markingapi.feature.users.dto;

import jakarta.validation.constraints.NotBlank;

public record RoleRequest(
        @NotBlank
        String name
) {
}
