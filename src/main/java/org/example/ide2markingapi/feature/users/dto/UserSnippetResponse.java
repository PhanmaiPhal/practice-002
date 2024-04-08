package org.example.ide2markingapi.feature.users.dto;

import java.time.LocalDate;

public record UserSnippetResponse(
        String name,
        String gender,
        LocalDate dob,
        String studentIdCard
) {
}
