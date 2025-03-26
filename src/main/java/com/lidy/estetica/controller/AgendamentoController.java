package com.lidy.estetica.controller;

import com.lidy.estetica.model.Agendamento;
import com.lidy.estetica.model.dto.AgendamentoDTO;
import com.lidy.estetica.usecase.AgendamentoUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/agendamentos")
@RequiredArgsConstructor
@Tag(name = "Agendamentos", description = "API para gerenciamento de agendamentos")
public class AgendamentoController {

    private final AgendamentoUseCase agendamentoUseCase;

    @PostMapping
    @Operation(summary = "Criar novo agendamento", description = "Cria um novo agendamento para um cliente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Agendamento criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "422", description = "Não foi possível criar o agendamento")
    })
    public ResponseEntity<Agendamento> createAgendamento(
            @Valid @RequestBody AgendamentoDTO agendamentoRequest) {
        Agendamento agendamento = agendamentoUseCase.createAgendamento(
                agendamentoRequest.getData(),
                agendamentoRequest.getIdProcedimento(),
                agendamentoRequest.getQuantidadeProcedimento(),
                agendamentoRequest.getIdCliente(),
                agendamentoRequest.getIdFuncionario(),
                agendamentoRequest.getObservacoes());
        return ResponseEntity.status(HttpStatus.CREATED).body(agendamento);
    }

    @GetMapping
    @Operation(summary = "Listar todos os agendamentos", description = "Retorna uma lista de todos os agendamentos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de agendamentos retornada com sucesso")
    })
    public ResponseEntity<Page<Agendamento>> getAllAgendamentos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(agendamentoUseCase.getAllAgendamentos(pageable));
    }

    @GetMapping("/date")
    @Operation(summary = "Buscar agendamentos por data", description = "Retorna os agendamentos de uma data específica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de agendamentos retornada com sucesso")
    })
    public ResponseEntity<List<Agendamento>> getSpecificDate(
            @Parameter(description = "Data para filtrar os agendamentos", example = "2024-03-18")
            @RequestParam("data") LocalDate data) {
        return ResponseEntity.ok(agendamentoUseCase.getSpecificDate(data));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar agendamento por ID", description = "Retorna um agendamento específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Agendamento encontrado"),
        @ApiResponse(responseCode = "404", description = "Agendamento não encontrado")
    })
    public ResponseEntity<Agendamento> getAgendamentoById(
            @Parameter(description = "ID do agendamento", example = "1")
            @PathVariable Integer id) {
        return agendamentoUseCase.getAgendamentoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar agendamento", description = "Remove um agendamento específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Agendamento removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Agendamento não encontrado")
    })
    public ResponseEntity<Void> deleteAgendamento(
            @Parameter(description = "ID do agendamento", example = "1")
            @PathVariable Integer id) {
        if (!agendamentoUseCase.getAgendamentoById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        agendamentoUseCase.deleteAgendamento(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Agendamento>> getAgendamentosByClienteId(@PathVariable Integer clienteId) {
        return ResponseEntity.ok(agendamentoUseCase.getAgendamentosByClienteId(clienteId));
    }
}
