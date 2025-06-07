package br.com.fiap.globalsolution2025.dto.request;

public record ResetPasswordRequest(String token, String novaSenha) {}
