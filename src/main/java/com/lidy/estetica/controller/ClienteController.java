package com.lidy.estetica.controller;


import com.lidy.estetica.model.Agendamento;
import com.lidy.estetica.model.Cliente;
import com.lidy.estetica.model.dto.AgendamentoDTO;
import com.lidy.estetica.model.dto.ClienteDTO;
import com.lidy.estetica.model.mapper.ClienteMapper;
import com.lidy.estetica.usecase.AgendamentoUseCase;
import com.lidy.estetica.usecase.ClienteUseCase;
import com.lidy.estetica.usecase.ProcedimentoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteUseCase clienteUseCase;

    @Autowired
    private ClienteMapper clienteMapper;

    @PostMapping
    public ResponseEntity<Cliente> createCliente(@RequestBody ClienteDTO cliente) {
        clienteUseCase.createCliente(clienteMapper.toEntity(cliente));
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        List<Cliente> clientes = clienteUseCase.getAllClientes();
        return ResponseEntity.ok(clientes);
    }
}