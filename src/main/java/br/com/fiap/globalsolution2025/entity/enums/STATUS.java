package br.com.fiap.globalsolution2025.entity.enums;

public enum STATUS {
    CONCLUIDO("concluido"),
    ABERTO("aberto");
    private final String status;

    STATUS(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
