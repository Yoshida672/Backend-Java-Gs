package br.com.fiap.globalsolution2025.dto;

import br.com.fiap.globalsolution2025.entity.enums.STATUS;
import br.com.fiap.globalsolution2025.entity.enums.TIPO;
import org.springframework.hateoas.Link;

import java.util.UUID;

public record AlertaResponse(
        UUID id,
        TIPO tipo,
        String mensagem,
        Integer nivelRisco,
        STATUS status,
        Link link
) {}