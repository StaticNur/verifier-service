package ru.startup.verifier_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.startup.verifier_service.model.User;
import ru.startup.verifier_service.model.dto.UserDto;

/**
 * Mapper class to convert User entity to UserDto.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(User entity);

    User toEntity(UserDto dto);
}
