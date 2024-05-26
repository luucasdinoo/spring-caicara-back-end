package br.com.caicara.backend.model.entities.mapper;

import br.com.caicara.backend.model.dto.user.UserCreateDto;
import br.com.caicara.backend.model.dto.user.UserResponseDto;
import br.com.caicara.backend.model.entities.user.User;
import org.modelmapper.ModelMapper;

public class UserMapper {

    public static User toUser(UserCreateDto createDto) {
        return new ModelMapper().map(createDto, User.class);
    }

    public static UserResponseDto toDto(User user) {
        return new ModelMapper().map(user, UserResponseDto.class);
    }

}
