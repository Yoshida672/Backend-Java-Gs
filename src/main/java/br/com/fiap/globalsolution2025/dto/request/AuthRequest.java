package br.com.fiap.globalsolution2025.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AuthRequest(
        @NotBlank String email,
        @NotBlank String senha
) {}
