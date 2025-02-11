package com.lidy.estetica.controller;


import com.lidy.estetica.model.Procedimento;
import com.lidy.estetica.usecase.ProcedimentoUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/procedimentos")
public class ProcedimentoController {

    private ProcedimentoUseCase procedimentoUseCase;

    public ProcedimentoController(ProcedimentoUseCase procedimentoUseCase) {
        this.procedimentoUseCase = procedimentoUseCase;
    }

    @GetMapping
    public ResponseEntity<List<Procedimento>> getAllProcedimentos() {
        return ResponseEntity.ok(procedimentoUseCase.getAllProcedimentos());
    }

}
