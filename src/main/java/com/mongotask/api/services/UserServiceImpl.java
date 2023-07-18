package com.mongotask.api.services;

import com.mongotask.api.constant.UserFilterType;
import com.mongotask.api.domain.User;
import com.mongotask.api.exception.NoSuchFilterException;
import com.mongotask.api.mappers.UserMapper;
import com.mongotask.api.model.UserDTO;
import com.mongotask.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MongoTemplate mongoTemplate;
    private final UserMapper userMapper;
    private final SequenceGeneratorService sequenceGeneratorService;

    @Override
    public void createUser(UserDTO userDTO) {
        User user = userMapper.userDtoToUser(userDTO);
        user.setId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
        user.setCreatedDate(LocalDateTime.now());
        userRepository.save(user);
    }

    @Override
    public Optional<UserDTO> findUser(String userId) {
        return userRepository.findById(userId)
                             .map(userMapper::userToUserDto);
    }

    @Override
    public List<UserDTO> findAll(Map<String, String> map) {
        Query query = new Query();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            UserFilterType userFilterType = UserFilterType.getUserFilterTypeByFilterType(entry.getKey());
            if (userFilterType == null)
                throw new NoSuchFilterException("No such filter type " + entry.getKey());

            userFilterType.filterUser(query, entry.getValue());
        }

        query.with(Sort.by(map.getOrDefault("sort_order", "ASC"), map.getOrDefault("sort_field", "firstName")));
        query.with(PageRequest.of(Integer.parseInt(map.getOrDefault("page", "0")), Integer.parseInt(map.getOrDefault("size", "3"))));

        return mongoTemplate.find(query, User.class)
                .stream()
                .map(userMapper::userToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> updateUser(String userId, UserDTO userDTO) {
        Optional<User> userOptional =  userRepository.findById(userId);

        if (userOptional.isPresent())
            return userOptional.map(foundUser -> {
                                    foundUser.setFirstName(userDTO.getFirstName());
                                    foundUser.setLastName(userDTO.getLastName());
                                    foundUser.setEmail(userDTO.getEmail());
                                    return foundUser;})
                               .flatMap(user -> Optional.of(userRepository.save(user))
                               .map(userMapper::userToUserDto));
        else
            return Optional.empty();
    }

    @Override
    public Optional<UserDTO> patchUser(String userId, UserDTO userDTO) {
        Optional<User> userOptional =  userRepository.findById(userId);

        if (userOptional.isPresent())
            return userOptional.map(foundUser -> {
                                    if(StringUtils.hasText(userDTO.getFirstName()))
                                        foundUser.setFirstName(userDTO.getFirstName());

                                    if(StringUtils.hasText(userDTO.getLastName()))
                                        foundUser.setLastName(userDTO.getLastName());

                                    if(StringUtils.hasText(userDTO.getEmail()))
                                        foundUser.setEmail(userDTO.getEmail());

                                    return foundUser;})
                                .flatMap(user -> Optional.of(userRepository.save(user))
                                .map(userMapper::userToUserDto));
        else
            return Optional.empty();
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
