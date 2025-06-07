package br.com.fiap.globalsolution2025.controller;

import br.com.fiap.globalsolution2025.entity.DadosSensor;
import br.com.fiap.globalsolution2025.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
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
            Double avgTemp = service.getAverageTemperatureToday();
            return ResponseEntity.ok(avgTemp);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to calculate average temperature.");
        }
    }

    @GetMapping("/average-humidity-today")
    public ResponseEntity<?> getAverageHumidityToday() {
        try {
            Double avgHumidity = service.getAverageHumidityToday();
            return ResponseEntity.ok(avgHumidity);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to calculate average humidity.");
        }
    }

    @GetMapping("/between")
    public ResponseEntity<?> getReadingsBetween(
            @RequestParam("start") String startStr,
            @RequestParam("end") String endStr) {
        try {
            LocalDateTime start = LocalDateTime.parse(startStr);
            LocalDateTime end = LocalDateTime.parse(endStr);
            List<DadosSensor> data = service.getReadingsBetween(start, end);
            return ResponseEntity.ok(data);
        } catch (java.time.format.DateTimeParseException e) {
            return ResponseEntity.badRequest().body("Invalid date format. Use: yyyy-MM-ddTHH:mm:ss");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to fetch data between dates.");
        }
    }
}
