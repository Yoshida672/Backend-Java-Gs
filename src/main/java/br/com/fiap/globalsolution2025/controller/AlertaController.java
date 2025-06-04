package br.com.fiap.globalsolution2025.controller;


import br.com.fiap.globalsolution2025.dto.AlertaRequest;
import br.com.fiap.globalsolution2025.dto.AlertaResponse;
import br.com.fiap.globalsolution2025.entity.Alerta;
import br.com.fiap.globalsolution2025.mapper.AlertaMapper;
import br.com.fiap.globalsolution2025.service.AlertaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alertas")
public class AlertaController {

    private final AlertaService service;
    private final AlertaMapper mapper;

    public AlertaController(AlertaService service, AlertaMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<AlertaResponse> create(@RequestBody AlertaRequest request) throws Exception {
        Alerta alerta = service.save(request);
        AlertaResponse response =mapper.toResponse(alerta, true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlertaResponse> getById(@PathVariable UUID id) throws Exception {
        Alerta alerta = service.getById(id);
        return ResponseEntity.ok(mapper.toResponse(alerta, false));
    }

    @GetMapping
    public ResponseEntity<List<AlertaResponse>> getAll() {
        List<AlertaResponse> list = service.getAll()
                .stream()
                .map(alerta -> {
                    try {
                        return mapper.toResponse(alerta, true);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlertaResponse> update(@PathVariable UUID id, @RequestBody AlertaRequest request) throws Exception {
        Alerta alerta = service.update(id, request);
        return ResponseEntity.ok(mapper.toResponse(alerta, true));
    }
    @DeleteMapping("/{id}")
    public  void delete(@PathVariable UUID id) throws Exception{
        service.delete(id);
    }
}