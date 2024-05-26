package br.com.caicara.backend.controllers;

import br.com.caicara.backend.model.dto.user.UserCreateDto;
import br.com.caicara.backend.model.dto.user.UserResponseDto;
import br.com.caicara.backend.model.dto.user.UserUpdatePasswordDto;
import br.com.caicara.backend.model.entities.mapper.UserMapper;
import br.com.caicara.backend.model.entities.user.User;
import br.com.caicara.backend.model.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserCreateDto createDto){
        User user = userService.createUser(UserMapper.toUser(createDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDto(user));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAllUsers(){
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(UserMapper.toListDto(users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable UUID id){
        User user = userService.findUserById(id);
        return ResponseEntity.ok(UserMapper.toDto(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserByid(@PathVariable UUID id){
        userService.deleteUserById(id);
        return  ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable UUID id, @Valid @RequestBody UserUpdatePasswordDto dto){
        userService.updatePassword(id, dto.getCurrentPassword(), dto.getNewPassword(), dto.getConfirmPassword());
        return ResponseEntity.noContent().build();
    }

}
