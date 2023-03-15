package com.sam.emedia.gateway.configs;

import com.sam.emedia.gateway.components.JWTComponents;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtTokenAuthenticationFilter implements WebFilter {
    public static final String HEADER_PREFIX = "Bearer ";

    private final JWTComponents tokenProvider;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String token = resolveToken(exchange.getRequest());
        if (StringUtils.hasText(token) && !this.tokenProvider.isExpired(token)) {
            exchange = exchange.mutate().request(
                    addUserContextToRequest(exchange.getRequest(),
                            token)).build();
            Authentication authentication = new UsernamePasswordAuthenticationToken("user","123", null);
            return chain.filter(exchange)
                    .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));
        }
        return chain.filter(exchange);
    }

    private String resolveToken(ServerHttpRequest request) {
        String bearerToken = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(HEADER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }
    private ServerHttpRequest addUserContextToRequest(ServerHttpRequest request, String token) {
        int userId = (int) tokenProvider.getDataMap(token).get("id");
        HttpHeaders headers = new HttpHeaders();
        headers.add("userId", String.valueOf(userId));
        ServerHttpRequest newRequest = request.mutate()
                .headers(httpHeaders -> httpHeaders.set("X-UserId", String.valueOf(userId)))
                .build();
        return newRequest;
    }
}
