package com.mongotask.api.controller;


import com.mongotask.api.model.UserDTO;
import com.mongotask.api.request.PaginationRequest;
import com.mongotask.api.response.UserListResponse;
import com.mongotask.api.response.UserResponse;
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
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnit4.class)
public class UserControllerTest {
    @Mock
    private UserService userService;
    private UserDTO userDTO;
    private UserDTO userDTO2;

    @BeforeEach
    public void init() {
        userDTO = UserDTO.builder().email("aaaa@gmail.com")
                .firstName("John")
                .lastName("John")
                .build();

        userDTO2 = UserDTO.builder().email("aaaa@gmail.com")
                .firstName("John")
                .lastName("Smith")
                .build();
    }

    @Test
    public void createUser_Ok() {
        UserController userController = new UserController(userService);
        HttpServletRequest httpServletRequest = mock(RequestFacade.class);
        when(httpServletRequest.getRequestURI()).thenReturn("api/v1/user");

        ResponseEntity responseEntity = userController.createUser(userDTO, httpServletRequest);

        verify(userService, Mockito.times(1)).createUser(userDTO);
        assert responseEntity.getHeaders().get("Path").get(0).equals("api/v1/user");
        assert responseEntity.getStatusCode().equals(HttpStatus.CREATED);
    }

    @Test
    public void createUser_NotOk() {
        UserController userController = new UserController(userService);
        HttpServletRequest httpServletRequest = mock(RequestFacade.class);
        when(httpServletRequest.getRequestURI()).thenReturn("api/v1/user");
        userDTO.setId("1");

        ResponseEntity responseEntity = userController.createUser(userDTO, httpServletRequest);

        verify(userService, Mockito.times(0)).createUser(userDTO);
        assert responseEntity.getHeaders().get("Path").get(0).equals("api/v1/user");
        assert responseEntity.getHeaders().get("Error").get(0).equals("Id must be null");
        assert responseEntity.getStatusCode().equals(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void findUserById_Ok() {
        UserController userController = new UserController(userService);
        HttpServletRequest httpServletRequest = mock(RequestFacade.class);
        when(httpServletRequest.getRequestURI()).thenReturn("api/v1/user/1");
        userDTO.setId("1");
        when(userService.findUser("1")).thenReturn(Optional.of(userDTO));

        ResponseEntity<UserResponse> responseEntity = userController.getUserById("1", httpServletRequest);

        verify(userService, Mockito.times(1)).findUser("1");
        assert responseEntity.getHeaders().get("Path").get(0).equals("api/v1/user/1");
        assert responseEntity.getStatusCode().equals(HttpStatus.OK);
        assert responseEntity.getBody().getUserDTO().equals(userDTO);
    }

    @Test
    public void findUserById_NotOk() {
        UserController userController = new UserController(userService);
        HttpServletRequest httpServletRequest = mock(RequestFacade.class);
        when(httpServletRequest.getRequestURI()).thenReturn("api/v1/user/1");
        userDTO.setId("1");
        when(userService.findUser("1")).thenReturn(Optional.empty());

        ResponseEntity<UserResponse> responseEntity = userController.getUserById("1", httpServletRequest);

        verify(userService, Mockito.times(1)).findUser("1");
        assert responseEntity.getHeaders().get("Path").get(0).equals("api/v1/user/1");
        assert responseEntity.getStatusCode().equals(HttpStatus.NOT_FOUND);
    }

    @Test
    public void filterUsers_Ok() {
        Map<String, String> map = new HashMap<>();
        map.put("firstName", "John");
        PaginationRequest paginationRequest = new PaginationRequest(2, 0, "id", Sort.Direction.ASC);
        List<UserDTO> userDTOList = new ArrayList<>();
        userDTOList.add(userDTO);
        userDTOList.add(userDTO2);

        userDTO.setId("1");
        UserController userController = new UserController(userService);
        HttpServletRequest httpServletRequest = mock(RequestFacade.class);
        when(userService.findAll(map, paginationRequest)).thenReturn(userDTOList);

        ResponseEntity<UserListResponse> responseEntity = userController.filterUsers(map, paginationRequest, httpServletRequest);

        verify(userService, Mockito.times(1)).findAll(map, paginationRequest);
        assert responseEntity.getStatusCode().equals(HttpStatus.OK);
        assert responseEntity.getBody().getUserDTOList().get(0).equals(userDTO);
        assert responseEntity.getBody().getUserDTOList().size() == 2;
    }
}
