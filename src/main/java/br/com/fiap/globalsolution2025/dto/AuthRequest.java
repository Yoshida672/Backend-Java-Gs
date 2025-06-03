package br.com.fiap.globalsolution2025.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthRequest(
        @NotBlank String login,
        @NotBlank String senha
) {
}
