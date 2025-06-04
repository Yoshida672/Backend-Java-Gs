package br.com.fiap.globalsolution2025.controller;

import br.com.fiap.globalsolution2025.entity.DadosSensor;
import br.com.fiap.globalsolution2025.service.DashboardService;
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

    @GetMapping("/ultimas")
    public List<DadosSensor> ultimasLeituras() {
        return service.ultimasLeituras();
    }

    @GetMapping("/media-temperatura-hoje")
    public Double mediaTemp() {
        return service.mediaTemperaturaHoje();
    }

    @GetMapping("/media-umidade-hoje")
    public Double mediaUmidade() {
        return service.mediaUmidadeHoje();
    }

    @GetMapping("/entre")
    public List<DadosSensor> leiturasPorIntervalo(
            @RequestParam("inicio") String inicioStr,
            @RequestParam("fim") String fimStr
    ) {
        LocalDateTime inicio = LocalDateTime.parse(inicioStr);
        LocalDateTime fim = LocalDateTime.parse(fimStr);
        return service.leiturasEntre(inicio, fim);
    }
}
