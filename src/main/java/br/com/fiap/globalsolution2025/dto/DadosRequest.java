package br.com.fiap.globalsolution2025.dto;

public record DadosRequest(
        Double temperatura,
        Double umidade,
        Double indiceUv) {
}
