package br.com.fiap.globalsolution2025.repository;

import br.com.fiap.globalsolution2025.entity.DadosSensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DadosSensorRepository  extends JpaRepository<DadosSensor, UUID> {
        List<DadosSensor> findTop10ByOrderByDataHoraDesc();

}
