package com.example.virtualGallery.model.mapper;

import com.example.virtualGallery.model.dto.RegisterDTO;
import com.example.virtualGallery.model.dto.view.ViewUserDTO;
import com.example.virtualGallery.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "active", constant = "true")
    UserEntity userDtoToUserEntity(RegisterDTO registerDTO);

    ViewUserDTO userToUserDTO(UserEntity user);
}
