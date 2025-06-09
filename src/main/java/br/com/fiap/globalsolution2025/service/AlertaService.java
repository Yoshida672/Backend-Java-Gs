package br.com.fiap.globalsolution2025.service;

import br.com.fiap.globalsolution2025.dto.request.AlertaRequest;
import br.com.fiap.globalsolution2025.dto.request.UpdateAlertaRequest;
import br.com.fiap.globalsolution2025.entity.Alerta;
import br.com.fiap.globalsolution2025.mapper.AlertaMapper;
import br.com.fiap.globalsolution2025.repository.AlertaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.util.ClassUtils.isPresent;

@Service
public class AlertaService {

    private final AlertaRepository repository;
    private final AlertaMapper mapper;

    public AlertaService(AlertaRepository repository, AlertaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Alerta save(AlertaRequest request) {
        Alerta alerta = mapper.toEntity(request,new Alerta());
        return repository.save(alerta);
    }

    public Alerta getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Alerta não encontrado: " + id));
    }

    public List<Alerta> getAll() {
        return repository.findAll();
    }

    public Alerta update(Long id, UpdateAlertaRequest request) throws Exception  {
        Alerta alerta = repository.findById(id).orElseThrow(()-> new Exception("Filial não encontrada"));
        mapper.toEntity(request,alerta);
        return repository.save(alerta);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Alerta não encontrado: " + id);
        }
        repository.deleteById(id);
    }
}