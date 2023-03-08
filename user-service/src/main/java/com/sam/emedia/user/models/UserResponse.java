package com.sam.emedia.user.models;
import com.sam.emedia.user.entities.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
public class UserResponse {
    int id;
    String name;
    String email;
    String profile;
    String phone;
    String address;
    Instant created;
    int userType;
    public static UserResponse parsUser(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .profile(user.getProfile())
                .phone(user.getPhone())
                .created(user.getCreated())
                .userType(user.getUserType())
                .build();
    }
}
