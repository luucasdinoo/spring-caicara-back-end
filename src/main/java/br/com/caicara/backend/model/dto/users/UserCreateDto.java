package br.com.caicara.backend.model.dto.users;

import jakarta.validation.constraints.*;
import lombok.*;


@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class UserCreateDto {

    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 8, max = 8)
    private String password;

}