package com.mongotask.api.response;

import com.mongotask.api.model.UserDTO;
import lombok.Builder;
import lombok.Getter;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

@Getter
@Builder
public class UserResponse {
    @NonNull
    private UserDTO userDTO;
    private String path;
    private LocalDateTime timestamp;
    public static UserResponse buildUserResponse(String path, UserDTO userDTO) {
        return UserResponse.builder()
                .userDTO(userDTO)
                .path(path)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
