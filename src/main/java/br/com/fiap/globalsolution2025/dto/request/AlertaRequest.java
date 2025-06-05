package br.com.fiap.globalsolution2025.dto.request;

import br.com.fiap.globalsolution2025.entity.enums.STATUS;
import br.com.fiap.globalsolution2025.entity.enums.TIPO;

public record AlertaRequest(
        TIPO tipo,
        String mensagem,
        Integer nivelRisco,
        STATUS status
) {}