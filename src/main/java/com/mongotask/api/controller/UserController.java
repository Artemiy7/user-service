package com.mongotask.api.controller;

import com.mongotask.api.constant.UserFilterType;
import com.mongotask.api.model.UserDTO;
import com.mongotask.api.exception.NoSuchFilterException;
import com.mongotask.api.response.UserListResponse;
import com.mongotask.api.response.UserResponse;
import com.mongotask.api.response.error.ErrorResponse;
import com.mongotask.api.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Api(value = "Performs User CRUD operations")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    @ApiOperation(value = "Create User, id must be null validate User other fields on not null, not blank, email pattern e.g.: @gmail.com")
    @PostMapping
    public ResponseEntity createUser(@RequestBody @Valid UserDTO userDTO,
                                     final HttpServletRequest httpServletRequest) {
        HttpHeaders headers = new HttpHeaders();
        if (userDTO.getId() != null) {
            headers.add("Error", "Id must be null");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .headers(headers)
                    .build();
        }
        userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }

    @ApiOperation(value = "Find User by Id")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable String id,
                                                    final HttpServletRequest httpServletRequest) {
        Optional<UserDTO> optionalUser = userService.findUser(id);

        if (optionalUser.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .build();

        log.info("user found: " + id);
        UserResponse userResponse = UserResponse.buildUserResponse(httpServletRequest.getRequestURI(), optionalUser.get());
        return ResponseEntity.ok()
                .body(userResponse);
    }

    @ApiOperation(value = "Find User list, filter on User fields, sorting, paging")
    @GetMapping
    public ResponseEntity<UserListResponse> filterUsers(@RequestParam Map<String, String> map,
                                                        final HttpServletRequest httpServletRequest) {
        HttpHeaders headers = new HttpHeaders();
        try {
            List<UserDTO> list = userService.findAll(map);
            UserListResponse userListResponse = UserListResponse.buildUserResponse(httpServletRequest.getRequestURI(), list);
            return ResponseEntity.ok()
                    .body(userListResponse);

        } catch (NoSuchFilterException noSuchFilterException) {
            log.error("Wrong filter type or value " + noSuchFilterException.getMessage());
            headers.add("Error", "Wrong filter type or value " + noSuchFilterException.getMessage());

            return new ResponseEntity(ErrorResponse.builder()
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message(noSuchFilterException.getMessage())
                    .timestamp(LocalDateTime.now())
                    .path(httpServletRequest.getRequestURI() + httpServletRequest.getQueryString())
                    .build(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Update ALL User object fields by Id")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable String id,
                                                   @RequestBody @Valid UserDTO userDTO,
                                                   final HttpServletRequest httpServletRequest) {
        HttpHeaders headers = new HttpHeaders();

        Optional<UserDTO> optionalUser = userService.updateUser(id, userDTO);

        if (optionalUser.isEmpty()) {
            log.info("no such user " + userDTO.getId());
            headers.add("Error", "No such user");
            return ResponseEntity.notFound()
                    .headers(headers)
                    .build();
        }
        log.info("user updated: user" + userDTO.getId());
        UserResponse userResponse = UserResponse.buildUserResponse(httpServletRequest.getRequestURI(), optionalUser.get());
        return ResponseEntity.ok()
                .headers(headers)
                .body(userResponse);
    }

    @ApiOperation(value = "Update some distinct User fields by Id")
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> patchUser(@PathVariable String id,
                                                  @RequestBody UserDTO userDTO,
                                                  final HttpServletRequest httpServletRequest) {
        HttpHeaders headers = new HttpHeaders();

        Optional<UserDTO> optionalUser = userService.patchUser(id, userDTO);

        if (optionalUser.isEmpty()) {
            log.info("no such user " + userDTO.getId());
            headers.add("Error", "No such user");
            return ResponseEntity.notFound()
                    .headers(headers)
                    .build();
        }
        log.info("user updated: user" + userDTO.getId());
        UserResponse userResponse = UserResponse.buildUserResponse(httpServletRequest.getRequestURI(), optionalUser.get());
        return ResponseEntity.ok()
                .headers(headers)
                .body(userResponse);
    }

    @ApiOperation(value = "Delete User by Id")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        log.info("user deleted: user" + id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Returns number of User filter parameters")
    @GetMapping(value = "/filters")
    public ResponseEntity<List<UserFilterType>> fetchUserFilters() {
        return ResponseEntity.ok()
                .body(UserFilterType.getUserFilterTypeList());
    }
}
