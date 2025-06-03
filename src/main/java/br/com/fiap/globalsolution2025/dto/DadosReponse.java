package br.com.fiap.globalsolution2025.dto;

import org.springframework.hateoas.Link;

import java.time.LocalDateTime;
import java.util.UUID;

public record DadosReponse(
        UUID id,
        Double temperatura,
        Double umidade,
        Double indiceUv,
        LocalDateTime data,
        Link link
) {
}
