package com.mindhub.user_service.services.Impl;

import com.mindhub.user_service.mappers.UserMapper;
import com.mindhub.user_service.dtos.UserDTO;
import com.mindhub.user_service.models.UserEntity;
import com.mindhub.user_service.repositories.UserRepository;
import com.mindhub.user_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Mono<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Flux<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Mono<UserEntity> createUser(UserDTO user) {
        UserEntity entity = userMapper.toUserEntity(user);
        entity.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(entity);
    }

    @Override
    public Mono<UserEntity> updateUser(Long id, UserDTO user) {
        return userRepository.findById(id)
                .flatMap(existingUser -> {
                    existingUser.setName(user.getName());
                    existingUser.setEmail(user.getEmail());
                    existingUser.setPassword(user.getPassword());
                    return userRepository.save(existingUser);
                });
    }

    @Override
    public Mono<Void> deleteUser(Long id) {
        return userRepository.findById(id)
                .flatMap(userRepository::delete);
    }

    @Override
    public Mono<UserEntity> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


}
