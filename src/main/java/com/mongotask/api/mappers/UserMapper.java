package com.mongotask.api.mappers;

import com.mongotask.api.domain.User;
import com.mongotask.api.model.UserDTO;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User userDtoToUser(UserDTO userDTO);

    UserDTO userToUserDto(User user);
}
