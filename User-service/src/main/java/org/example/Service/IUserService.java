package org.example.Service;

import org.example.DTO.Request.EditDto;
import org.example.DTO.Request.LoginDto;
import org.example.DTO.Request.SignupDto;
import org.example.DTO.Response.UserResponseDto;

import java.util.List;

public interface IUserService {

    UserResponseDto addUser(SignupDto signupDto);

//    UserResponseDto getByUsername(String userName);
//
//
//    void deleteById(Long id);
//
    UserResponseDto getUserById(Long userId);
//
//    UserResponseDto UpdateUser(EditDto editDto);
//
//    UserResponseDto getUserLogin(LoginDto loginDto);
//
//    List<UserResponseDto> getAllUsers();

}