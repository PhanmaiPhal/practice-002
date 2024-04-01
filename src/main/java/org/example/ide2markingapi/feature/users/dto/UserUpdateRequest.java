package org.example.ide2markingapi.feature.users.dto;

import java.time.LocalDate;

public record UserUpdateRequest(
        String name,
        String gender,
        LocalDate dob,
        String studentCardId

) {
}
