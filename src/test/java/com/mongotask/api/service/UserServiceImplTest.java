package com.mongotask.api.service;

import com.mongotask.api.domain.User;
import com.mongotask.api.mappers.UserMapper;
import com.mongotask.api.model.UserDTO;
import com.mongotask.api.repositories.UserRepository;
import com.mongotask.api.services.SequenceGeneratorService;
import com.mongotask.api.services.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;
import java.util.*;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@RunWith(JUnit4.class)
public class UserServiceImplTest {
    private UserRepository userRepository = mock(UserRepository.class);
    private MongoTemplate mongoTemplate = mock(MongoTemplate.class);
    private UserMapper userMapper = mock(UserMapper.class);
    private SequenceGeneratorService sequenceGeneratorService = mock(SequenceGeneratorService.class);
    private UserServiceImpl userService = new UserServiceImpl(userRepository, mongoTemplate, userMapper, sequenceGeneratorService);
    private UserDTO userDTO;
    private User user;


    @BeforeEach
    public void init() {
        userDTO = UserDTO.builder()
                .id("1")
                .email("aaaa@gmail.com")
                .firstName("John")
                .lastName("Smith")
                .build();

        user = User.builder()
                .id("1")
                .email("aaa@gmail.com")
                .firstName("John")
                .lastName("Smith")
                .build();
    }

    @Test
    public void createUser_Ok() {
        when(userMapper.userDtoToUser(userDTO)).thenReturn(user);
        when(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME)).thenReturn("1");

        userService.createUser(userDTO);

        verify(userRepository, Mockito.times(1)).save(user);
        verify(userMapper, Mockito.times(1)).userDtoToUser(userDTO);
        verify(sequenceGeneratorService, Mockito.times(1)).generateSequence(User.SEQUENCE_NAME);
    }

    @Test
    public void findUser_Ok() {
        when(userRepository.findById("1")).thenReturn(Optional.of(user));
        when(userMapper.userToUserDto(user)).thenReturn(userDTO);

        Optional<UserDTO> optionalUserDTO = userService.findUser("1");

        verify(userRepository, Mockito.times(1)).findById("1");
        verify(userMapper, Mockito.times(1)).userToUserDto(user);
        assert optionalUserDTO.get() == userDTO;
    }

    @Test
    public void findUser_NotOk() {
        when(userRepository.findById("1")).thenReturn(Optional.empty());

        Optional<UserDTO> optionalUserDTO = userService.findUser("1");

        verify(userRepository, Mockito.times(1)).findById("1");
        assert optionalUserDTO.isEmpty();
    }

    @Test
    public void updateUser_Ok() {
        userDTO.setEmail("bbbb@gmail.com");
        userDTO.setFirstName("Joe");
        userDTO.setLastName("Johnson");
        when(userRepository.findById("1")).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.userToUserDto(user)).thenReturn(userDTO);

        Optional<UserDTO> optionalUserDTO = userService.updateUser("1", userDTO);

        verify(userRepository, Mockito.times(1)).findById("1");
        verify(userRepository, Mockito.times(1)).save(user);
        verify(userMapper, Mockito.times(1)).userToUserDto(user);
        assert optionalUserDTO.get().equals(userDTO);
    }

    @Test
    public void updateUser_NotOk() {
        when(userRepository.findById("1")).thenReturn(Optional.empty());
        when(userMapper.userToUserDto(user)).thenReturn(userDTO);

        Optional<UserDTO> optionalUserDTO = userService.updateUser("1", userDTO);

        verify(userRepository, Mockito.times(1)).findById("1");
        verify(userRepository, Mockito.times(0)).save(user);
        verify(userMapper, Mockito.times(0)).userToUserDto(user);
        assert optionalUserDTO.isEmpty();
    }

    @Test
    public void patchUser_Ok() {
        userDTO.setLastName("Johnson");
        when(userRepository.findById("1")).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.userToUserDto(user)).thenReturn(userDTO);

        Optional<UserDTO> optionalUserDTO = userService.patchUser("1", userDTO);

        verify(userRepository, Mockito.times(1)).findById("1");
        verify(userRepository, Mockito.times(1)).save(user);
        verify(userMapper, Mockito.times(1)).userToUserDto(user);
        assert optionalUserDTO.get().getLastName().equals("Johnson");
    }

    @Test
    public void patchUser_NotOk() {
        userDTO.setLastName("Johnson");
        when(userRepository.findById("1")).thenReturn(Optional.empty());
        when(userMapper.userToUserDto(user)).thenReturn(userDTO);

        Optional<UserDTO> optionalUserDTO = userService.patchUser("1", userDTO);

        verify(userRepository, Mockito.times(1)).findById("1");
        verify(userRepository, Mockito.times(0)).save(user);
        verify(userMapper, Mockito.times(0)).userToUserDto(user);
        assert optionalUserDTO.isEmpty();
    }

    @Test
    public void deleteUser_Ok() {
        userService.deleteUser("1");

        verify(userRepository, Mockito.times(1)).deleteById("1");
    }
}
