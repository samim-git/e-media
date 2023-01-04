package com.sam.emedia.gateway.configs;

import org.json.JSONObject;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Component
public class BasicAuthEntryPoint implements ServerAuthenticationEntryPoint {

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException e) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(UNAUTHORIZED);
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, "application/json");
        JSONObject resultMap = new JSONObject();
        resultMap.put("success",false);
        resultMap.put("timestamp",new Date().getTime());
        resultMap.put("status",UNAUTHORIZED);
        resultMap.put("error","Full authentication is required to access this resource");
        resultMap.put("path",exchange.getRequest().getPath());

        byte[] bytes = resultMap.toString().getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(bytes);
        return exchange.getResponse().writeWith(Mono.just(buffer));
    }
}
