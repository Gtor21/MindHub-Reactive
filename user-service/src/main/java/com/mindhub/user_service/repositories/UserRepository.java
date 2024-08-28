package com.mindhub.user_service.repositories;

import com.mindhub.user_service.models.UserEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserRepository extends ReactiveCrudRepository<UserEntity, Long> {
}
