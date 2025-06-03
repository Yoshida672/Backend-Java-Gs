package br.com.fiap.globalsolution2025.dto;


import br.com.fiap.globalsolution2025.entity.enums.USER_ROLE;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterRequest(
        @NotBlank String login,
        @NotBlank String senha,
        @NotNull USER_ROLE role
        ) {
}
