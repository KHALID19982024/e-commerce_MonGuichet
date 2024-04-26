package org.example.DTO.Request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class EditDto {
    Long id;

    @NotEmpty(message = "Name can not be null or empty")
    @Size(min = 3, max = 50, message = "Full name must be between 3 and 50 characters")
    String fullName;

    @NotNull(message = "Password must not be null")
    @Size(min = 6, max = 30, message = "Password must be between 6 and 30 characters")
    String password;
}
