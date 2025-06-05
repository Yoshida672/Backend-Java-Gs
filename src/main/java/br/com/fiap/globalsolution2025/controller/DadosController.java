package br.com.fiap.globalsolution2025.controller;

import br.com.fiap.globalsolution2025.dto.request.DadosPorSensorRequest;
import br.com.fiap.globalsolution2025.dto.response.DadosReponse;
import br.com.fiap.globalsolution2025.dto.request.DadosRequest;
import br.com.fiap.globalsolution2025.entity.Usuario;
import br.com.fiap.globalsolution2025.mapper.DadosMapper;
import br.com.fiap.globalsolution2025.repository.UsuarioRepository;
import br.com.fiap.globalsolution2025.service.DadosSensorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final UsuarioRepository usuarioRepository;

    public DadosController(DadosSensorService service, DadosMapper mapper, UsuarioRepository usuarioRepository) {
        this.service = service;
        this.mapper = mapper;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<DadosReponse> create(@RequestBody @Valid DadosRequest request) throws Exception {
        String emailUsuario = SecurityContextHolder.getContext().getAuthentication().getName();

        Usuario usuario = service.getUsuarioPorEmail(emailUsuario);

        DadosSensor saved = service.saveDataComUsuario(request, usuario);

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

    @PostMapping("/publico")
    public ResponseEntity<Void> createBySensor(@RequestBody @Valid DadosPorSensorRequest request) {
        try {
            service.saveDataComToken(request);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


}