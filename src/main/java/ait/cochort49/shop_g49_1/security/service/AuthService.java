package ait.cochort49.shop_g49_1.security.service;


import ait.cochort49.shop_g49_1.security.dto.LoginRequestDTO;
import ait.cochort49.shop_g49_1.security.dto.TokenResponseDTO;
import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;



@Service
public class AuthService {

    private final TokenService tokenService;
    private final UserDetailsService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    // username : refreshToken
    private final Map<String, String> refreshStorage;


    public AuthService(TokenService tokenService, UserDetailsService userService, BCryptPasswordEncoder passwordEncoder) {
        this.tokenService = tokenService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.refreshStorage = new HashMap<>();
    }

    /*
    1. Принять данные пользователя
    2. Проверка логина и пароля
    3. Генерация токенов
    4. Сохранить refresh-токен
    5. Формирование ответа
     */

    public TokenResponseDTO login(LoginRequestDTO loginRequestDTO) throws AuthException {

        String username = loginRequestDTO.username();

        UserDetails foundUser = userService.loadUserByUsername(username);

        if (passwordEncoder.matches(loginRequestDTO.password(), foundUser.getPassword())) {
            String accessToken = tokenService.generateAccessToken(foundUser);
            String refreshToken = tokenService.generateRefreshToken(foundUser);

            refreshStorage.put(username, refreshToken);

            return new TokenResponseDTO(accessToken, refreshToken);
        }

        throw new AuthException("Incorrect login and / or password");
    }

    /*
    1. Прием рефреш-токена
    2. Валидация токена
    3. Извлечь информацию о пользователе
    4. Проверка в хранилище токенов refreshStorage
    5. Получение данных о пользователе из базы
    6. Генерация нового Access Token
    7. Формирование ответа
     */

    public TokenResponseDTO refreshAccessToken(String refreshToken) throws AuthException {

        boolean isValid = tokenService.validateRefreshToken(refreshToken);

        Claims refreshClaims = tokenService.getRefreshClaimsFromToken(refreshToken);

        String username = refreshClaims.getSubject();

        String savedToken = refreshStorage.getOrDefault(username, null);
        boolean isSaved = savedToken != null && savedToken.equals(refreshToken);

        if (isValid && isSaved) {
            UserDetails foundUser = userService.loadUserByUsername(username);

            String accessToken = tokenService.generateAccessToken(foundUser);

            return new TokenResponseDTO(accessToken, refreshToken);
        }

        throw new AuthException("Incorrect refresh token. Re login please");
    }



}
