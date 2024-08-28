package com.mindhub.user_service.mappers;

import com.mindhub.user_service.models.Dto.UserDTO;
import com.mindhub.user_service.models.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class UserMapper {

    public UserEntity toUserEntity(UserDTO DTO){
        UserEntity entity = new UserEntity();

        if (DTO == null){
            return null;
        }

        entity.setId(DTO.getId());
        entity.setName(DTO.getName());
        entity.setPassword(DTO.getPassword());
        entity.setEmail(DTO.getEmail());

        return entity;
    }
}
