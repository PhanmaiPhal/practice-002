package org.example.ide2markingapi.feature.users.dto;

import java.time.LocalDate;

public record UserResponse(
        String uuid,
        String name,
        String profileImage,
        String gender,
        LocalDate dob
) {
}
