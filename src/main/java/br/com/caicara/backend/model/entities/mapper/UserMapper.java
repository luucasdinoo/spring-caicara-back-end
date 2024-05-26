package br.com.caicara.backend.model.entities.mapper;

import br.com.caicara.backend.model.dto.user.UserCreateDto;
import br.com.caicara.backend.model.dto.user.UserResponseDto;
import br.com.caicara.backend.model.entities.user.User;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static User toUser(UserCreateDto createDto) {
        return new ModelMapper().map(createDto, User.class);
    }

    public static UserResponseDto toDto(User user) {
        return new ModelMapper().map(user, UserResponseDto.class);
    }

    public static List<UserResponseDto> toListDto(List<User> users) {
        return users.stream().map(user -> toDto(user)).collect(Collectors.toList());
    }

}
