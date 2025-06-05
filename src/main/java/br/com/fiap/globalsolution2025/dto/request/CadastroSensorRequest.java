package br.com.fiap.globalsolution2025.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CadastroSensorRequest(
        @NotBlank(message = "O token do dispositivo é obrigatório")
        String deviceToken,

        @NotBlank(message = "O nome do sensor é obrigatório")
        String nomeSensor
) {}
