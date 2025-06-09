package br.com.fiap.globalsolution2025.service;

import br.com.fiap.globalsolution2025.entity.DadosSensor;
import br.com.fiap.globalsolution2025.repository.DadosSensorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DashboardService {

    private final DadosSensorRepository repository;

    public DashboardService(DadosSensorRepository repository) {
        this.repository = repository;
    }

    public List<DadosSensor> getLatestReadings() {
        return repository.findTop10ByOrderByDataHoraDesc();
    }

    public Optional<Double> getAverageTemperatureToday() {
        LocalDate today = LocalDate.now();
        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.plusDays(1).atStartOfDay();
        Double average = repository.mediaTemperatura(start, end);
        return Optional.ofNullable(average);
    }


    public Optional<Double> getAverageHumidityToday() {
        LocalDate today = LocalDate.now();
        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.plusDays(1).atStartOfDay();

        Double average = repository.mediaUmidade(start, end);
        return Optional.ofNullable(average);
    }

    public List<DadosSensor> getReadingsBetween(LocalDateTime start, LocalDateTime end) {
        return repository.findByDataHoraBetween(start, end);
    }
}
