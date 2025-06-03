package br.com.fiap.globalsolution2025.service;

import br.com.fiap.globalsolution2025.dto.DadosRequest;
import br.com.fiap.globalsolution2025.entity.DadosSensor;
import br.com.fiap.globalsolution2025.mapper.DadosMapper;
import br.com.fiap.globalsolution2025.repository.DadosSensorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DadosSensorService {

    private final DadosSensorRepository repository;
    private final DadosMapper mapper;

    public DadosSensorService(DadosSensorRepository repository, DadosMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
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

    public DadosSensor update(UUID id, DadosRequest dto) {
        if(repository.findById(id).isPresent()){
            DadosSensor data = mapper.toEntity(dto);
            return repository.save(data);
        }
        else{
            return null;
        }

    }

}
