package br.com.fiap.globalsolution2025.dto;


import br.com.fiap.globalsolution2025.entity.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(
        @NotBlank String login,
        @NotBlank String senha,
        @NotNull UserRole role
        ) {
}
