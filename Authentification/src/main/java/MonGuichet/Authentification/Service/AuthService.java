package MonGuichet.Authentification.Service;

import MonGuichet.Authentification.Client.UserServiceClient;
import MonGuichet.Authentification.DTO.RegisterDto;
import MonGuichet.Authentification.DTO.TokenDto;
import MonGuichet.Authentification.DTO.request.LoginRequest;
import MonGuichet.Authentification.DTO.request.RegisterRequest;
import MonGuichet.Authentification.Exception.WrongCredentialsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserServiceClient userServiceClient;
    private final JwtService jwtService;


    public TokenDto login(LoginRequest request) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        if (authenticate.isAuthenticated())
            return TokenDto
                    .builder()
                    .token(jwtService.generateToken(request.getUsername()))
                    .build();
        else throw new WrongCredentialsException("Wrong credentials");
    }

    public RegisterDto register(RegisterRequest request) {
        return userServiceClient.save(request).getBody();
    }
}