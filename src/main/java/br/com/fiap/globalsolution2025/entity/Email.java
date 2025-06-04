package br.com.fiap.globalsolution2025.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "ssx_emails")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    private boolean ativo;

    public Email() {}

    public Email(String email, boolean ativo) {
        this.email = email;
        this.ativo = ativo;
    }

    // Getters e setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return email;
    }
}
