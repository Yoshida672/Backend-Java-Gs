package br.com.fiap.globalsolution2025.repository;

import br.com.fiap.globalsolution2025.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SensorRepository extends JpaRepository<Sensor, UUID> {
    Optional<Sensor> findByDeviceToken(String deviceToken);
}
