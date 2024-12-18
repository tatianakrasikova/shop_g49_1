package ait.cochort49.shop_g49_1.security.controller;


import ait.cochort49.shop_g49_1.security.dto.LoginRequestDTO;
import ait.cochort49.shop_g49_1.security.dto.RefreshRequestDTO;
import ait.cochort49.shop_g49_1.security.dto.TokenResponseDTO;
import ait.cochort49.shop_g49_1.security.service.AuthService;
import jakarta.security.auth.message.AuthException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // POST "auth/login"
    @PostMapping("/login")
    public TokenResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {

        try {
            return authService.login(loginRequestDTO);
        } catch (AuthException e) {
            throw new RuntimeException(e);
        }
    }


    @PostMapping("/refresh")
    public  TokenResponseDTO refreshToken(@RequestBody RefreshRequestDTO refreshRequestDTO)  {
        try {
            return authService.refreshAccessToken(refreshRequestDTO.getRefreshToken());
        } catch (AuthException e) {
            throw new RuntimeException(e);
        }
    }
}
