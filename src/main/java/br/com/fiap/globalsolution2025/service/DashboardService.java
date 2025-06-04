package br.com.fiap.globalsolution2025.service;

import br.com.fiap.globalsolution2025.entity.DadosSensor;
import br.com.fiap.globalsolution2025.repository.DadosSensorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DashboardService {

    private final DadosSensorRepository repository;

    public DashboardService(DadosSensorRepository repository) {
        this.repository = repository;
    }

    public List<DadosSensor> ultimasLeituras() {
        return repository.findTop10ByOrderByDataHoraDesc();
    }

    public Double mediaTemperaturaHoje() {
        LocalDateTime inicio = LocalDate.now().atStartOfDay();
        LocalDateTime fim = inicio.plusDays(1).minusSeconds(1);
        return repository.mediaTemperatura(inicio, fim);
    }

    public Double mediaUmidadeHoje() {
        LocalDateTime inicio = LocalDate.now().atStartOfDay();
        LocalDateTime fim = inicio.plusDays(1).minusSeconds(1);
        return repository.mediaUmidade(inicio, fim);
    }

    public List<DadosSensor> leiturasEntre(LocalDateTime inicio, LocalDateTime fim) {
        return repository.findByDataHoraBetween(inicio, fim);
    }
}
