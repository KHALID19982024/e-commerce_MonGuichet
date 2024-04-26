package org.example.Mapper;

import org.example.DTO.Request.EditDto;
import org.example.DTO.Request.SignupDto;
import org.example.DTO.Response.UserResponseDto;
import org.example.Entity.User;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING)
public interface IUserMapper {
    User toUser(UserResponseDto userResponseDto);

    @Mapping(target = "password",
            expression = "java(password)")
    User toUser(
            SignupDto signupDto,
            @Context
            String password
    );

    User toUser(EditDto editDto);

    UserResponseDto toDto(User user);

    void partialUpdate(
            UserResponseDto userResponseDto,
            @MappingTarget
            User user
    );

    void partialUpdate(
            SignupDto signupDto,
            @MappingTarget
            User user
    );

    void partialUpdate(
            EditDto editDto,
            @MappingTarget
            User user
    );
}