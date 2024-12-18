package ait.cochort49.shop_g49_1.security.filter;


import ait.cochort49.shop_g49_1.security.AuthInfo;
import ait.cochort49.shop_g49_1.security.service.TokenService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;



@Component
public class TokenFilter extends GenericFilterBean {

    private final TokenService tokenService;

    public TokenFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        /*
        1. Извлечь токен из запроса
        2. Валидация токена
        3. Авторизация пользователя
        4. Продолжение цепочки фильтров
         */

        // Authorization: Bearer <токен>

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String accessToken = getTokenFromRequest(request);

        if (accessToken != null && tokenService.validateAccessToken(accessToken)) {
            // Мы получили access токен из заголовков и он прошел валидацию
            // Нам нужно авторизовать пользователя


            // Извлекаем информацию о пользователе из токена
            Claims claims = tokenService.getAccessClaimsFromToken(accessToken);
            AuthInfo authInfo = tokenService.mapClaimsToAuthInfo(claims);


            // Устанавливаем пользователя как аутентифицированного
            authInfo.setAuthenticated(true);

            // Поместить объект AuthInfo в securityContext
            SecurityContextHolder.getContext().setAuthentication(authInfo);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String getTokenFromRequest(HttpServletRequest request) {

        String bearerToken = request.getHeader("Authorization");

        // Проверяем что заголовок не пустой и начинается с "Bearer "
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        // Если токен пустой или не начинается с "Bearer "
        return null;

    }
}
