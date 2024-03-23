package br.com.lucianoneves.agendadecontatos.controllers;

import br.com.lucianoneves.agendadecontatos.dtos.AgendaDeContatosDto;
import br.com.lucianoneves.agendadecontatos.dtos.AgendaDeContatosDtoParam;
import br.com.lucianoneves.agendadecontatos.services.AgendaDeContatosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agenda-de-contatos")
public class AgendaDeContatosController {
    final AgendaDeContatosService agendaDeContatosService;

    public AgendaDeContatosController(AgendaDeContatosService agendaDeContatosService) {
        this.agendaDeContatosService = agendaDeContatosService;
    }

    @PostMapping("/cadastrar-contato")
    public ResponseEntity<Object> criarContatoController(@RequestBody AgendaDeContatosDtoParam agendaDeContatosDtoParam) {
        return agendaDeContatosService.criarContatoService(agendaDeContatosDtoParam);
    }

    @GetMapping("/listar-contatos")
    public ResponseEntity<List<AgendaDeContatosDto>> listarTodosContatosController() {
        return agendaDeContatosService.listarTodosContatosService();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> pegarUmContatoController(@PathVariable(value = "id") Integer id) {
        return agendaDeContatosService.pegarUmContatoService(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarUmContatoController(
            @PathVariable(value = "id") Integer id,
            @RequestBody AgendaDeContatosDtoParam agendaDeContatosDtoParam
    ) {
        return agendaDeContatosService.atualizarUmContatoService(id, agendaDeContatosDtoParam);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarUmContatoController(@PathVariable(value = "id") Integer id) {
        return agendaDeContatosService.deletarUmContatoService(id);
    }
}
