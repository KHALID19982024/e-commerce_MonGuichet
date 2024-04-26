package MonGuichet.Authentification.Client;

import MonGuichet.Authentification.DTO.RegisterDto;
import MonGuichet.Authentification.DTO.UserDto;
import MonGuichet.Authentification.DTO.request.RegisterRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user2-microservice")
public interface UserServiceClient {
    @PostMapping("/api/v1/user/register")
    ResponseEntity<RegisterDto> save(@RequestBody RegisterRequest request);

    @GetMapping(value = "/api/v1/user/username", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDto> getUserByUsername(@RequestParam String username);

}