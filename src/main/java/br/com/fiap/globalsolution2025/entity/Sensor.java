package br.com.fiap.globalsolution2025.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Embeddable
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;
    private String deviceToken;

    @ManyToOne
    @JoinColumn(name = "usuario")
    private Usuario usuario;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Sensor() {
    }

    public Sensor(UUID id, String nome, String deviceToken, Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.deviceToken = deviceToken;
        this.usuario = usuario;
    }
}

