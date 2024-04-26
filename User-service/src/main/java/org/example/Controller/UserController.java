package org.example.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.DTO.Request.EditDto;
import org.example.DTO.Request.LoginDto;
import org.example.DTO.Request.SignupDto;
import org.example.DTO.Response.UserResponseDto;
import org.example.Service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value= "/api/v1/user")
public class UserController {

    private final IUserService userService;


    @PostMapping("/create")
    public ResponseEntity<UserResponseDto> addUser(@Valid @RequestBody SignupDto signupDto) {
        var newUser=this.userService.addUser(signupDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
//
//    @GetMapping("/{username}")
//    public ResponseEntity<UserResponseDto> getByUsername(@PathVariable String username) {
//        return ResponseEntity.ok(userService.getByUsername(username));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
//        userService.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }
//
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }
//
//    @PutMapping("/{userId}")
//    public ResponseEntity<UserResponseDto> UpdateUser(@PathVariable Long userId, @RequestBody EditDto editDto) {
//        editDto.setId(userId);
//        return ResponseEntity.ok(userService.UpdateUser(editDto));
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<UserResponseDto> getUserLogin(@RequestBody LoginDto loginDto) {
//        return ResponseEntity.ok(userService.getUserLogin(loginDto));
//    }
//
//    @GetMapping
//    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
//        return ResponseEntity.ok(userService.getAllUsers());
//    }
}
