package com.presencesystem.controller.dto.mappers;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.presencesystem.controller.dto.UserDTO;
import com.presencesystem.dao.domain.User;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO map(User entity);

    User map(UserDTO dto);

    @IterableMapping(qualifiedByName = { "defaultMapper" })
    Iterable<UserDTO> map(Iterable<User> content);
}