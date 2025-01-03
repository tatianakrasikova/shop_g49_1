package ait.cochort49.shop_g49_1.security.config;

import ait.cochort49.shop_g49_1.security.filter.TokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private TokenFilter tokenFilter;

    public SecurityConfig(TokenFilter tokenFilter) {
        this.tokenFilter = tokenFilter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    //Base64: login:password

    // GET /products
    // GET /products/{id}
    // POST /products
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(ses -> ses.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(AbstractHttpConfigurer::disable)
                .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth
                        //чтобы подключить аутентификацию надо закомментировать
                        //эту строку ниже и раскомментировать все остальные
                        //строки ниже кроме того где 2 слеша
                                // Временно отключаем Security
                                .anyRequest().permitAll()
                              //  .requestMatchers(HttpMethod.POST, "/auth/login", "/auth/refresh").permitAll()
                             //   .requestMatchers(HttpMethod.GET,"/products").permitAll()
                             //   .requestMatchers(HttpMethod.GET, "/products/{id}").authenticated()
//    //                    .requestMatchers(HttpMethod.GET, "products/{id}").hasAnyRole("USER", "ADMIN")
                           //     .requestMatchers(HttpMethod.POST, "/products").hasRole("ADMIN")
                            //    .anyRequest().authenticated()

                );

      return   http.build();

    }
}
