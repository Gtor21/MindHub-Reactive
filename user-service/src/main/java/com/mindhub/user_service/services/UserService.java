package com.mindhub.user_service.services;

import com.mindhub.user_service.models.Dto.UserDTO;
import com.mindhub.user_service.models.UserEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<UserEntity> getUserById(Long id);
    Flux<UserEntity> getAllUsers();
    Mono<UserEntity> createUser(UserDTO user);
    Mono<UserEntity> updateUser(Long id, UserDTO user);
    Mono<Void> deleteUser(Long id);
}
