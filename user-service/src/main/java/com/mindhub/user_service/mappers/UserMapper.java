package com.mindhub.user_service.mappers;

import com.mindhub.user_service.dtos.UserDTO;
import com.mindhub.user_service.models.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toUserEntity(UserDTO DTO){
        UserEntity entity = new UserEntity();

        if (DTO == null){
            return null;
        }

        entity.setId(DTO.getId());
        entity.setName(DTO.getName());
        entity.setEmail(DTO.getEmail());

        return entity;
    }

    public static UserDTO toUserDTO(UserEntity entity){
        UserDTO DTO = new UserDTO();

        if (entity == null){
            return null;
        }

        DTO.setId(entity.getId());
        DTO.setName(entity.getName());
        DTO.setEmail(entity.getEmail());

        return DTO;
    }
}
