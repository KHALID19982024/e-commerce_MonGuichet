package org.example.Mapper;

import org.example.DTO.Request.SignupDto;
import org.example.DTO.Response.UserResponseDto;
import org.example.Entity.User;

public class UserMapper {

    public static SignupDto mapTosignupDto(SignupDto signupDto, User user){
        signupDto.setFullName(user.getFullName());
        signupDto.setUsername(user.getUsername());
        signupDto.setPassword(user.getPassword());
        signupDto.setEmail(user.getEmail());
        signupDto.setGender(user.getGender());
        signupDto.setPhone(user.getPhone());
        return signupDto;
    }

    public static User mapSignupToUser(SignupDto signupDto, User user){
        user.setFullName(signupDto.getFullName());
        user.setUsername(signupDto.getUsername());
        user.setPassword(signupDto.getPassword());
        user.setEmail(signupDto.getEmail());
        user.setGender(signupDto.getGender());
        user.setPhone(signupDto.getPhone());
        return user;
    }

    public static UserResponseDto mapToUserResponseDto(UserResponseDto userResponseDto, User user){
        userResponseDto.setId(user.getId());
        userResponseDto.setFullName(user.getFullName());
        userResponseDto.setUsername(user.getUsername());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setGender(user.getGender());
        userResponseDto.setPhone(user.getPhone());
        return userResponseDto;
    }

    public static User mapUserdtoToUser(UserResponseDto userResponseDto, User user){
        user.setId(userResponseDto.getId());
        user.setFullName(userResponseDto.getFullName());
        user.setUsername(userResponseDto.getUsername());
        user.setEmail(userResponseDto.getEmail());
        user.setGender(userResponseDto.getGender());
        user.setPhone(userResponseDto.getPhone());
        return user;
    }

}
