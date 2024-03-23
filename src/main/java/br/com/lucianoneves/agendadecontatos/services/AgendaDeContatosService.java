package br.com.lucianoneves.agendadecontatos.services;

import br.com.lucianoneves.agendadecontatos.dtos.AgendaDeContatosDto;
import br.com.lucianoneves.agendadecontatos.dtos.AgendaDeContatosDtoParam;
import br.com.lucianoneves.agendadecontatos.entities.AgendaDeContatosEntity;
import br.com.lucianoneves.agendadecontatos.repositories.AgendaDeContatosRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AgendaDeContatosService {
    final AgendaDeContatosRepository agendaDeContatosRepository;

    public AgendaDeContatosService(AgendaDeContatosRepository agendaDeContatosRepository) {
        this.agendaDeContatosRepository = agendaDeContatosRepository;
    }

    public ResponseEntity<Object> criarContatoService(AgendaDeContatosDtoParam agendaDeContatosDtoParam) {
        if (agendaDeContatosRepository.existsByTelefone(agendaDeContatosDtoParam.getTelefone())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Conflito: número de telefone já existe!");
        } else if (agendaDeContatosRepository.existsByEmail(agendaDeContatosDtoParam.getEmail())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Conflito: endereço de e-mail já existe!");
        }

        AgendaDeContatosEntity agendaDeContatosEntity = new AgendaDeContatosEntity();
        agendaDeContatosEntity.setDataDoCadastro(LocalDateTime.now());
        BeanUtils.copyProperties(agendaDeContatosDtoParam, agendaDeContatosEntity);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(agendaDeContatosRepository.save(agendaDeContatosEntity));
    }

    public ResponseEntity<List<AgendaDeContatosDto>> listarTodosContatosService() {
        List<AgendaDeContatosEntity> agendaDeContatosEntityList = agendaDeContatosRepository.findAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(converterParaListaDTO(agendaDeContatosEntityList));
    }

    public List<AgendaDeContatosDto> converterParaListaDTO(List<AgendaDeContatosEntity> agendaDeContatosEntityList) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        List<AgendaDeContatosDto> agendaDeContatosDtoList = new ArrayList<>();

        agendaDeContatosEntityList.stream().forEach(agendaDeContatosEntity -> {

            AgendaDeContatosDto agendaDeContatosDto = new AgendaDeContatosDto();
            agendaDeContatosDto.setNome(agendaDeContatosEntity.getNome());
            agendaDeContatosDto.setEmail(agendaDeContatosEntity.getEmail());
            agendaDeContatosDto.setTelefone(agendaDeContatosEntity.getTelefone());
            agendaDeContatosDto.setDataDoCadastro(
                    agendaDeContatosEntity.getDataDoCadastro() != null
                            ? formatter.format(agendaDeContatosEntity.getDataDoCadastro())
                            : null
            );
            agendaDeContatosDto.setDataDaAlteracao(
                    agendaDeContatosEntity.getDataDaAlteracao() != null
                            ? formatter.format(agendaDeContatosEntity.getDataDaAlteracao())
                            : null
            );

            agendaDeContatosDtoList.add(agendaDeContatosDto);
        });

        return agendaDeContatosDtoList;
    }

    public ResponseEntity<Object> pegarUmContatoService(Integer id) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Optional<AgendaDeContatosDto> agendaDeContatosDto = agendaDeContatosRepository.findById(id).map(resultado -> {

            AgendaDeContatosDto agendaDeContatosDtoResultado = new AgendaDeContatosDto();
            agendaDeContatosDtoResultado.setNome(resultado.getNome());
            agendaDeContatosDtoResultado.setEmail(resultado.getEmail());
            agendaDeContatosDtoResultado.setTelefone(resultado.getTelefone());
            agendaDeContatosDtoResultado.setDataDoCadastro(formatter.format(resultado.getDataDoCadastro()));
            agendaDeContatosDtoResultado.setDataDaAlteracao(
                    resultado.getDataDaAlteracao() != null
                            ? formatter.format(resultado.getDataDaAlteracao())
                            : null
            );

            return agendaDeContatosDtoResultado;
        });

        if (agendaDeContatosDto.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(agendaDeContatosDto);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Id não localizado");
        }
    }


    public ResponseEntity<Object> atualizarUmContatoService(Integer id, AgendaDeContatosDtoParam agendaDeContatosDtoParam) {
        Optional<AgendaDeContatosEntity> agendaDeContatosEntityOptional = agendaDeContatosRepository.findById(id);

        if (!agendaDeContatosEntityOptional.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Id não encontrado.");
        }

        AgendaDeContatosEntity agendaDeContatosEntity = new AgendaDeContatosEntity();
        BeanUtils.copyProperties(agendaDeContatosDtoParam, agendaDeContatosEntity);
        agendaDeContatosEntity.setId(agendaDeContatosEntityOptional.get().getId());

        if (agendaDeContatosDtoParam.getNome() != null) {
            agendaDeContatosEntity.setNome(agendaDeContatosDtoParam.getNome());
        } else {
            agendaDeContatosEntity.setNome(agendaDeContatosEntityOptional.get().getNome());
        }
        if (agendaDeContatosDtoParam.getEmail() != null) {
            agendaDeContatosEntity.setEmail(agendaDeContatosDtoParam.getEmail());
        } else {
            agendaDeContatosEntity.setEmail(agendaDeContatosEntityOptional.get().getEmail());
        }
        if (agendaDeContatosDtoParam.getTelefone() != null) {
            agendaDeContatosEntity.setTelefone(agendaDeContatosDtoParam.getTelefone());
        } else {
            agendaDeContatosEntity.setTelefone(agendaDeContatosEntityOptional.get().getTelefone());
        }

        agendaDeContatosEntity.setDataDoCadastro(agendaDeContatosEntityOptional.get().getDataDoCadastro());
        agendaDeContatosEntity.setDataDaAlteracao(LocalDateTime.now());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(agendaDeContatosRepository.save(agendaDeContatosEntity));
    }

    public ResponseEntity<Object> deletarUmContatoService(Integer id) {
        Optional<AgendaDeContatosEntity> agendaDeContatosEntity = agendaDeContatosRepository.findById(id);

        if (agendaDeContatosEntity.isPresent()) {
            agendaDeContatosRepository.delete(agendaDeContatosEntity.get());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Objeto excluído com sucesso!");
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Id não encontrado.");
        }
    }
}
