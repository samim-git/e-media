package com.sam.emedia.user.components;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class HttpServletComponent {
    final HttpServletRequest request;
    public int getUserId(){
        return request.getIntHeader("X-UserId");
    }
}
