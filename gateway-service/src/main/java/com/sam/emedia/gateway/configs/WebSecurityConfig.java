package com.sam.emedia.gateway.configs;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebFluxSecurity
public class WebSecurityConfig {
    private static final String[] PUBLIC_URLS = {"/auth/user/**", "/eureka/**"};
    final BasicAuthEntryPoint basicAuthEntryPoint;
    final JwtTokenAuthenticationFilter jwtTokenAuthenticationFilter;

    @Bean
    SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange()
                .pathMatchers(PUBLIC_URLS).permitAll()
                .anyExchange().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(basicAuthEntryPoint)
                .and().addFilterAt(jwtTokenAuthenticationFilter,SecurityWebFiltersOrder.HTTP_BASIC)
                .cors().and().csrf().disable();
        return http.build();
    }

    @Bean
    public PasswordEncoder myPasswordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

}
