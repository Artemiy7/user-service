package com.mongotask.api.bootstrap;

import com.mongotask.api.domain.User;
import com.mongotask.api.repositories.UserRepository;
import com.mongotask.api.services.SequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final SequenceGeneratorService sequenceGeneratorService;

    @Override
    public void run(String... args) {
        loadBeerData();
    }

    private void loadBeerData() {
        User user = User.builder()
                .id(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME))
                .firstName("John")
                .lastName("Smith")
                .email("email@gmail.com")
                .build();

        User user2 = User.builder()
                .id(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME))
                .firstName("Elon")
                .lastName("Musk")
                .email("email@gmail.com")
                .build();

        User user3 = User.builder()
                .id(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME))
                .firstName("Jonathan")
                .lastName("Smithson")
                .email("email@gmail.com")
                .build();

        userRepository.save(user);
        userRepository.save(user2);
        userRepository.save(user3);
    }
}
