package org.example.Service;

import lombok.AllArgsConstructor;
import org.example.DTO.Request.EditDto;
import org.example.DTO.Request.LoginDto;
import org.example.DTO.Request.SignupDto;
import org.example.DTO.Response.UserResponseDto;
import org.example.Entity.User;
import org.example.Exception.UserException;
import org.example.Mapper.IUserMapper;
import org.example.Mapper.UserMapper;
import org.example.Repository.UserRepository;
import org.springframework.stereotype.Service;
import org.example.Utils.HashPassword;
import java.util.List;

@Service
@AllArgsConstructor

public class UserServiceImp implements IUserService{

    private final UserRepository userRepository;
//    private final IUserMapper userMapper;


    @Override
    public UserResponseDto addUser(final SignupDto signupDto) {
        User user = UserMapper.mapSignupToUser(signupDto,new User());
        var exist = this.userRepository.existsUser(user);
        if(exist){
            throw new UserException(UserException.USER_EXIST);
        }
        var saveUser=this.userRepository.save(user);
        return UserMapper.mapToUserResponseDto(new UserResponseDto(),saveUser);
    }



//    @Override
//    public UserResponseDto getByUsername(String userName) {
//        var user= this.userRepository.findByUsername(userName)
//                .orElseThrow(() -> new UserException(UserException.USER_NOT_FOUND));
//        return this.userMapper.toDto(user);
//    }

//    @Override
//    public void deleteById(Long id) {
//        var user = this.userRepository.findById(id)
//                .orElseThrow(() -> new UserException(UserException.USER_NOT_FOUND));
//        this.userRepository.deleteById(id);
//    }
//
    @Override
    public UserResponseDto getUserById(Long userId) {
        var user= this.userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserException.USER_NOT_FOUND));
        return UserMapper.mapToUserResponseDto(new UserResponseDto(),user);
    }
//
//    @Override
//    public UserResponseDto UpdateUser(EditDto editDto) {
//        var user= this.userRepository.findById(editDto.getId())
//                .orElseThrow(() -> new UserException(UserException.USER_NOT_FOUND));
//        this.userMapper.partialUpdate(editDto, user);
//        return this.userMapper.toDto(this.userRepository.save(user));
//    }
//
//    @Override
//    public UserResponseDto getUserLogin(LoginDto loginDto) {
//        var pw = HashPassword.sha256Hash(loginDto.getPassword());
//        var user = this.userRepository.findByUsernameAndPassword(loginDto.getUsername(),pw)
//                .orElseThrow(() -> new UserException(UserException.USER_NOT_FOUND));
//        return this.userMapper.toDto(user);
//    }
//
//    @Override
//    public List<UserResponseDto> getAllUsers() {
//            return userRepository.findAll()
//                    .stream()
//                    .map(userMapper::toDto).toList();
//    }
}
