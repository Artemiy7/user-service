package com.mongotask.api.controller;


import com.mongotask.api.model.UserDTO;
import com.mongotask.api.services.UserService;
import org.apache.catalina.connector.RequestFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnit4.class)
public class UserControllerTest {
    @Mock
    private UserService userService;
    private UserDTO userDTO;

    @BeforeEach
    public void init() {
        userDTO = UserDTO.builder().email("aaaa@gmail.com")
                .firstName("John")
                .lastName("John")
                .build();
    }

    @Test
    public void createUser_Ok() {
        UserController userController = new UserController(userService);
        HttpServletRequest httpServletRequest = mock(RequestFacade.class);
        when(httpServletRequest.getRequestURI()).thenReturn("api/v1/user");

        ResponseEntity occasionDtoResponseEntity = userController.createUser(userDTO, httpServletRequest);

        verify(userService, Mockito.times(1)).createUser(userDTO);
        assert occasionDtoResponseEntity.getHeaders().get("Path").get(0).equals("api/v1/user");
        assert occasionDtoResponseEntity.getStatusCode().equals(HttpStatus.CREATED);
    }

    @Test
    public void createUser_NotOk() {
        UserController userController = new UserController(userService);
        HttpServletRequest httpServletRequest = mock(RequestFacade.class);
        when(httpServletRequest.getRequestURI()).thenReturn("api/v1/user");
        userDTO.setId("1");

        ResponseEntity occasionDtoResponseEntity = userController.createUser(userDTO, httpServletRequest);

        verify(userService, Mockito.times(0)).createUser(userDTO);
        assert occasionDtoResponseEntity.getHeaders().get("Path").get(0).equals("api/v1/user");
        assert occasionDtoResponseEntity.getHeaders().get("Error").get(0).equals("Id must be null");
        assert occasionDtoResponseEntity.getStatusCode().equals(HttpStatus.BAD_REQUEST);
    }
}
