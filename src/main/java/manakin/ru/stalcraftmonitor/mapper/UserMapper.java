package manakin.ru.stalcraftmonitor.mapper;

import manakin.ru.stalcraftmonitor.dto.UserDto;
import manakin.ru.stalcraftmonitor.entity.UserEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {
    public UserDto toUserDto(UserEntity userEntity);
    public UserEntity toUserEntity(UserDto userDto);
}
