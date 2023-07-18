package com.mongotask.api.services;

import com.mongotask.api.model.UserDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    void createUser(UserDTO userDTO);

    Optional<UserDTO> findUser(String userId);

    List<UserDTO> findAll(Map<String, String> map);

    Optional<UserDTO> updateUser(String userId, UserDTO userDTO);

    Optional<UserDTO> patchUser(String userId, UserDTO userDTO);

    void deleteUser(String id);
}
