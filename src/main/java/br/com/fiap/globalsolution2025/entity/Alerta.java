package br.com.fiap.globalsolution2025.entity;

import br.com.fiap.globalsolution2025.entity.enums.STATUS;
import br.com.fiap.globalsolution2025.entity.enums.TIPO;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "sslx_alertas")
public class Alerta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_alerta")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_alerta")
    private TIPO tipo;

    private String mensagem;

    @Column(name = "nivel_risco")
    private Integer nivelRisco;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private STATUS status;

    @ManyToOne
    @JoinColumn(name = "id_leitura_sensor")
    private DadosSensor leituraSensor;

    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    public Alerta() {
    }

    public Alerta(UUID id, TIPO tipo, String mensagem, Integer nivelRisco, STATUS status, DadosSensor leituraSensor, LocalDateTime dataHora) {
        this.id = id;
        this.tipo = tipo;
        this.mensagem = mensagem;
        this.nivelRisco = nivelRisco;
        this.status = status;
        this.leituraSensor = leituraSensor;
        this.dataHora = dataHora;
    }

    public UUID getId() {
        return id;
    }

    public TIPO getTipo() {
        return tipo;
    }

    public void setTipo(TIPO tipo) {
        this.tipo = tipo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Integer getNivelRisco() {
        return nivelRisco;
    }

    public void setNivelRisco(Integer nivelRisco) {
        this.nivelRisco = nivelRisco;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public DadosSensor getLeituraSensor() {
        return leituraSensor;
    }

    public void setLeituraSensor(DadosSensor leituraSensor) {
        this.leituraSensor = leituraSensor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
