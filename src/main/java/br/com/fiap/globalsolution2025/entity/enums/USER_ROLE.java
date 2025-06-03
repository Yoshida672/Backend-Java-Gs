package br.com.fiap.globalsolution2025.entity.enums;

public enum USER_ROLE {
    ADMIN("admin"),
    USER("user"),
    GUEST("guest");
    private final String role;

    USER_ROLE(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
