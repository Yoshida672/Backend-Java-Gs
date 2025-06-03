package br.com.fiap.globalsolution2025.mapper;

import br.com.fiap.globalsolution2025.controller.DadosController;
import br.com.fiap.globalsolution2025.dto.DadosReponse;
import br.com.fiap.globalsolution2025.dto.DadosRequest;
import br.com.fiap.globalsolution2025.entity.DadosSensor;
import br.com.fiap.globalsolution2025.repository.DadosSensorRepository;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DadosMapper {

    public DadosSensor toEntity(DadosRequest dto){
        DadosSensor data = new DadosSensor();
        data.setTemperatura(dto.temperatura());
        data.setUmidade(dto.umidade());
        data.setIndiceUv(dto.indiceUv());
        data.setDataHora(LocalDateTime.now());
        return data;
    }


        public DadosReponse toResponse(DadosSensor dto, boolean self) throws Exception {
            Link link;
            if (self) {
                link = linkTo(methodOn(DadosController.class).getById(dto.getId())).withSelfRel();
            } else {
                link = linkTo(methodOn(DadosController.class).getAll()).withRel("Lista de Dados");
            }
            return new DadosReponse(
                    dto.getId(),
                    dto.getTemperatura(),
                    dto.getUmidade(),
                    dto.getIndiceUv(),
                    dto.getDataHora(),
                    link
            );
        }
    }

