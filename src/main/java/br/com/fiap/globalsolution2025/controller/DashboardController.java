package br.com.fiap.globalsolution2025.controller;

import br.com.fiap.globalsolution2025.dto.request.BetweenRequest;
import br.com.fiap.globalsolution2025.entity.DadosSensor;
import br.com.fiap.globalsolution2025.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping("/latest")
    public ResponseEntity<?> getLatestReadings() {
        try {
            List<DadosSensor> data = service.getLatestReadings();
            return ResponseEntity.ok(data);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to fetch latest readings.");
        }
    }

    @GetMapping("/average-temperature-today")
    public ResponseEntity<?> getAverageTemperatureToday() {
        try {
            Optional<Double> avgTemp = service.getAverageTemperatureToday();
            if (avgTemp.isPresent()) {

                return ResponseEntity.ok(avgTemp.get());
            }else{
                return ResponseEntity.ok(
                        Map.of("mensagem", "Nenhuma leitura registrada hoje")
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao calcular a temperatura média.");
        }
    }

    @GetMapping("/average-humidity-today")
    public ResponseEntity<?> getAverageHumidityToday() {
        try {
            Optional<Double> avgHumidity = service.getAverageHumidityToday();
            if (avgHumidity.isPresent()) {
                return ResponseEntity.ok(avgHumidity.get());
            } else {
                return ResponseEntity.ok(
                        Map.of("mensagem", "Nenhuma leitura registrada hoje")
                );
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao calcular a umidade média.");
        }
    }

    @PostMapping("/between")
    public ResponseEntity<?> getReadingsBetween(@RequestBody BetweenRequest intervalo) {
        try {
            var start = LocalDateTime.parse(intervalo.startStr());
            var end = LocalDateTime.parse(intervalo.endStr());

            var data = service.getReadingsBetween(start, end);
            return ResponseEntity.ok(data);

        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body("Formato de data inválido. Use: yyyy-MM-ddTHH:mm:ss");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao buscar dados entre datas.");
        }
    }

}
