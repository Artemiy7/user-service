package com.mongotask.api.response;

import com.mongotask.api.model.UserDTO;
import lombok.Builder;
import lombok.Getter;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class UserListResponse {
    @NonNull
    private List<UserDTO> userDTOList;
    private String path;
    private LocalDateTime timestamp;
    public static UserListResponse buildUserResponse(String path, List<UserDTO> userDTOList) {
        return UserListResponse.builder()
                .userDTOList(userDTOList)
                .path(path)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
