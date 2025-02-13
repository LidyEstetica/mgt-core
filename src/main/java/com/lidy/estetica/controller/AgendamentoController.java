package com.lidy.estetica.controller;

import com.lidy.estetica.model.Agendamento;
import com.lidy.estetica.model.dto.AgendamentoDTO;
import com.lidy.estetica.usecase.AgendamentoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoUseCase agendamentoUseCase;

    @PostMapping("/cliente")
    public ResponseEntity<Agendamento> createAgendamento(@RequestBody AgendamentoDTO agendamentoRequest) {
        boolean agendamento = agendamentoUseCase.createAgendamento(
                agendamentoRequest.getData(),
                agendamentoRequest.getIdProcedimento(),
                agendamentoRequest.getQuantidadeProcedimento(),
                agendamentoRequest.getIdCliente(),
                agendamentoRequest.getIdFuncionario());
        if (!agendamento) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping
    public ResponseEntity<List<Agendamento>> getAllAgendamentos() {
        List<Agendamento> agendamentos = agendamentoUseCase.getAllAgendamentos();
        return ResponseEntity.ok(agendamentos);
    }

    @GetMapping("/date")
    public ResponseEntity<List<Agendamento>> getSpecificDate(@RequestParam("data") LocalDate data) {
        return ResponseEntity.ok(agendamentoUseCase.getSpecificDate(data));
    }

}
