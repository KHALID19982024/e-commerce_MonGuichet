package org.example.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class SignupDto {

    private String fullName;

    private String username;

    private String password;

    private String email;

    private String gender;

    private String phone;



}