package br.com.fiap.globalsolution2025.service;


import br.com.fiap.globalsolution2025.dto.request.CadastroSensorRequest;
import br.com.fiap.globalsolution2025.entity.Sensor;
import br.com.fiap.globalsolution2025.entity.Usuario;
import br.com.fiap.globalsolution2025.repository.SensorRepository;
import br.com.fiap.globalsolution2025.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final SensorRepository sensorRepository;
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, SensorRepository sensorRepository) {
        this.usuarioRepository = usuarioRepository;
        this.sensorRepository = sensorRepository;
    }

    // CRUD

    @Transactional
    @CachePut(value = "usuarios", key = "#result.id")
    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Cacheable(value = "usuarios", key = "#id")
    public Usuario readUsuarioById(UUID id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Cacheable(value = "usuarios", key = "'todos'")
    public List<Usuario> readUsuarios() {
        return usuarioRepository.findAll();
    }

    @Transactional
    @CachePut(value = "usuarios", key = "#result.id")
    public Usuario updateUsuario(UUID id, Usuario usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isEmpty()) {
            return null;
        }
        usuario.setId(id);
        return usuarioRepository.save(usuario);
    }

    public Sensor cadastrarSensorParaUsuario(String emailUsuario, CadastroSensorRequest request) {
        Usuario usuario = usuarioRepository.findByEmail_Email(emailUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (sensorRepository.findByDeviceToken(request.deviceToken()).isPresent()) {
            throw new RuntimeException("Dispositivo com esse token já existe");
        }

        Sensor sensor = new Sensor();
        sensor.setDeviceToken(request.deviceToken());
        sensor.setNome(request.nomeSensor());
        sensor.setUsuario(usuario);

        return sensorRepository.save(sensor);
    }



    @Transactional
    @CacheEvict(value = "usuarios", key = "#id")
    public void deleteUsuario(UUID id) {
        usuarioRepository.deleteById(id);
        limparCacheTodosUsuarios(); // Atualiza o cache de usuarios
        //limparTodoCacheUsuarios(); // Apaga todos os usuarios do cache
    }

    @CacheEvict(value = "usuarios", key = "'todos'")
    public void limparCacheTodosUsuarios() {}

    @CacheEvict(value = "usuarios", allEntries = true)
    public void limparTodoCacheUsuarios() {}

}
