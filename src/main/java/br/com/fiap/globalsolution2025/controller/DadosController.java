package br.com.fiap.globalsolution2025.controller;

import br.com.fiap.globalsolution2025.dto.DadosReponse;
import br.com.fiap.globalsolution2025.dto.DadosRequest;
import br.com.fiap.globalsolution2025.mapper.DadosMapper;
import br.com.fiap.globalsolution2025.service.DadosSensorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.fiap.globalsolution2025.entity.DadosSensor;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/dados")
public class DadosController {

    private final DadosSensorService service;
    private final DadosMapper mapper;

    public DadosController(DadosSensorService service, DadosMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<DadosReponse> create(@RequestBody DadosRequest request) throws Exception {
        System.out.println("Requisição recebida: " + request);
        DadosSensor saved = service.saveData(request);
        DadosReponse response = mapper.toResponse(saved, true);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DadosReponse> getById(@PathVariable UUID id) throws Exception {
        DadosSensor entity = service.getById(id);
        DadosReponse response = mapper.toResponse(entity, false);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<DadosReponse>> getAll() {
        List<DadosReponse> list = service.getAll().stream()
                .map(d -> {
                    try {
                        return mapper.toResponse(d, true);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(list);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DadosReponse> update(@PathVariable UUID id, @RequestBody DadosRequest request) throws Exception {
        DadosSensor updated = service.update(id, request);
        DadosReponse response = mapper.toResponse(updated, true);
        return ResponseEntity.ok(response);
    }
}