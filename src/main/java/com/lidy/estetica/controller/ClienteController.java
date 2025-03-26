package com.lidy.estetica.controller;


import com.lidy.estetica.model.Agendamento;
import com.lidy.estetica.model.Cliente;
import com.lidy.estetica.model.dto.ClienteDTO;
import com.lidy.estetica.model.mapper.ClienteMapper;
import com.lidy.estetica.service.AgendamentoService;
import com.lidy.estetica.usecase.ClienteUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private static final Logger log = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private ClienteUseCase clienteUseCase;

    @Autowired
    private ClienteMapper clienteMapper;

    @Autowired
    private AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody ClienteDTO cliente) {
        clienteUseCase.createCliente(clienteMapper.toEntity(cliente));
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        log.info("Recebida requisição para buscar todos os clientes");
        return ResponseEntity.ok(clienteUseCase.getAllClientes());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Integer id) {
        clienteUseCase.deleteClienteEmCascata(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/cascata")
    public ResponseEntity<Void> deleteClienteEmCascata(@PathVariable Integer id) {
        clienteUseCase.deleteClienteEmCascata(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {
        cliente.setId(Long.valueOf(id));
        return ResponseEntity.ok(clienteUseCase.updateCliente(cliente));
    }

    @GetMapping("/{id}/agendamentos")
    public ResponseEntity<List<Agendamento>> getAgendamentosByClienteId(@PathVariable Integer id) {
        return ResponseEntity.ok(clienteUseCase.getAgendamentosCliente(id));
    }
}