package br.com.fiap.globalsolution2025.controller;

import br.com.fiap.globalsolution2025.dto.request.CadastroSensorRequest;
import br.com.fiap.globalsolution2025.entity.Sensor;
import br.com.fiap.globalsolution2025.entity.Usuario;
import br.com.fiap.globalsolution2025.repository.UsuarioRepository;
import br.com.fiap.globalsolution2025.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;
    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository, UsuarioService usuarioService) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return ResponseEntity.ok(usuarios);
    }
    @PostMapping("/sensores")
    public ResponseEntity<?> cadastrarSensor(
            @RequestBody @Valid CadastroSensorRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {

        String email = userDetails.getUsername();
        Sensor sensor = usuarioService.cadastrarSensorParaUsuario(email, request);

        return ResponseEntity.ok("Sensor cadastrado com sucesso com ID: " + sensor.getId());
    }

}
