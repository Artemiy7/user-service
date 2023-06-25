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
    private UserService userService = mock(UserService.class);
    private HttpServletRequest httpServletRequest = mock(RequestFacade.class);
    private UserController userController = new UserController(userService);
    private UserDTO userDTO;
    private UserDTO userDTO2;


    @BeforeEach
    public void init() {
        userDTO = UserDTO.builder()
                .id("1")
                .email("aaaa@gmail.com")
                .firstName("John")
                .lastName("John")
                .build();

        userDTO2 = UserDTO.builder()
                .id("2")
                .email("aaaa@gmail.com")
                .firstName("John")
                .lastName("Smith")
                .build();
    }

    @Test
    public void createUser_Ok() {
        userDTO.setId(null);
        ResponseEntity responseEntity = userController.createUser(userDTO, httpServletRequest);

        verify(userService, Mockito.times(1)).createUser(userDTO);
        assert responseEntity.getStatusCode().equals(HttpStatus.CREATED);
    }

    @Test
    public void createUser_NotOk() {
        ResponseEntity responseEntity = userController.createUser(userDTO, httpServletRequest);

        verify(userService, Mockito.times(0)).createUser(userDTO);
        assert responseEntity.getHeaders().get("Error").get(0).equals("Id must be null");
        assert responseEntity.getStatusCode().equals(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void getUserById_Ok() {
        when(userService.findUser("1")).thenReturn(Optional.of(userDTO));

        ResponseEntity<UserResponse> responseEntity = userController.getUserById("1", httpServletRequest);

        verify(userService, Mockito.times(1)).findUser("1");
        assert responseEntity.getStatusCode().equals(HttpStatus.OK);
        assert responseEntity.getBody().getUserDTO().equals(userDTO);
    }

    @Test
    public void getUserById_NotOk() {
        when(userService.findUser("1")).thenReturn(Optional.empty());

        ResponseEntity<UserResponse> responseEntity = userController.getUserById("1", httpServletRequest);

        verify(userService, Mockito.times(1)).findUser("1");
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

        userDTO.setId("1");;
        when(userService.findAll(map, paginationRequest)).thenReturn(userDTOList);

        ResponseEntity<UserListResponse> responseEntity = userController.filterUsers(map, paginationRequest, httpServletRequest);

        verify(userService, Mockito.times(1)).findAll(map, paginationRequest);
        assert responseEntity.getStatusCode().equals(HttpStatus.OK);
        assert responseEntity.getBody().getUserDTOList().get(0).equals(userDTO);
        assert responseEntity.getBody().getUserDTOList().size() == 2;
    }

    @Test
    public void updateUser_Ok() {
        userDTO.setId("3");
        userDTO.setFirstName("Joe");
        userDTO.setFirstName("Smith");
        userDTO.setEmail("aaaa@gmail.com");
        when(userService.updateUser("1", userDTO)).thenReturn(Optional.of(userDTO));

        ResponseEntity<UserResponse> responseEntity = userController.updateUser("1", userDTO, httpServletRequest);

        verify(userService, Mockito.times(1)).updateUser("1", userDTO);
        assert responseEntity.getStatusCode().equals(HttpStatus.OK);
        assert responseEntity.getBody().getUserDTO().equals(userDTO);
    }

    @Test
    public void updateUser_NotOk() {
        userDTO.setId("3");
        userDTO.setFirstName("Joe");
        userDTO.setFirstName("Smith");
        userDTO.setEmail("aaaa@gmail.com");
        when(userService.updateUser("1", userDTO)).thenReturn(Optional.empty());

        ResponseEntity<UserResponse> responseEntity = userController.updateUser("1", userDTO, httpServletRequest);

        verify(userService, Mockito.times(1)).updateUser("1", userDTO);
        assert responseEntity.getStatusCode().equals(HttpStatus.NOT_FOUND);
    }

    @Test
    public void patchUser_Ok() {
        userDTO.setFirstName("Joe");
        when(userService.patchUser("1", userDTO)).thenReturn(Optional.of(userDTO));

        ResponseEntity<UserResponse> responseEntity = userController.patchUser("1", userDTO, httpServletRequest);

        verify(userService, Mockito.times(1)).patchUser("1", userDTO);
        assert responseEntity.getStatusCode().equals(HttpStatus.OK);
        assert responseEntity.getBody().getUserDTO().getFirstName().equals("Joe");
    }

    @Test
    public void patchUser_NotOk() {
        userDTO.setFirstName("Joe");
        when(userService.patchUser("1", userDTO)).thenReturn(Optional.empty());

        ResponseEntity<UserResponse> responseEntity = userController.patchUser("1", userDTO, httpServletRequest);

        verify(userService, Mockito.times(1)).patchUser("1", userDTO);
        assert responseEntity.getStatusCode().equals(HttpStatus.NOT_FOUND);
    }

    @Test
    public void deleteUser_Ok() {
        ResponseEntity responseEntity = userController.deleteUser("1");

        verify(userService, Mockito.times(1)).deleteUser("1");
        assert responseEntity.getStatusCode().equals(HttpStatus.OK);
    }
}
