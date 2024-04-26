package org.example.DTO.Response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserResponseDto {
    private Long id;
    private String fullName;
    private String username;
    private String email;
    private String gender;
    private String phone;
}
