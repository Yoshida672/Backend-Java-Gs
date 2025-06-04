package br.com.fiap.globalsolution2025.service;

import br.com.fiap.globalsolution2025.dto.DadosRequest;
import br.com.fiap.globalsolution2025.entity.DadosSensor;
import br.com.fiap.globalsolution2025.entity.Usuario;
import br.com.fiap.globalsolution2025.mapper.DadosMapper;
import br.com.fiap.globalsolution2025.repository.DadosSensorRepository;
import br.com.fiap.globalsolution2025.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DadosSensorService {

    private final DadosSensorRepository repository;
    private final DadosMapper mapper;
    private final UsuarioRepository usuarioRepository;
    public DadosSensorService(DadosSensorRepository repository, DadosMapper mapper, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.usuarioRepository = usuarioRepository;
    }

    public DadosSensor saveData(DadosRequest dto) {
        DadosSensor data = mapper.toEntity(dto);
        return repository.save(data);
    }

    public DadosSensor getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dados não encontrados para o ID: " + id));
    }

    public List<DadosSensor> getAll() {
        return repository.findAll();
    }
    public Usuario getUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail_Email(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
    public DadosSensor saveDataComUsuario(DadosRequest request, Usuario usuario) {
        DadosSensor dados = mapper.toEntity(request);
        dados.setUsuario(usuario); // supondo que sua entidade DadosSensor tenha um atributo Usuario
        return repository.save(dados);
    }
}
