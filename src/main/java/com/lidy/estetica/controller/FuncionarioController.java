package com.lidy.estetica.controller;

import com.lidy.estetica.model.Funcionario;
import com.lidy.estetica.usecase.FuncionarioUsecase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/funcionarios")
public class FuncionarioController {

    private static final Logger log = LoggerFactory.getLogger(FuncionarioController.class);

    @Autowired
    private FuncionarioUsecase funcionarioUseCase;

    @GetMapping
    public ResponseEntity<List<Funcionario>> getAllFuncionarios() {
        log.info("Recebida requisição para buscar todos os funcionários");
        return ResponseEntity.ok(funcionarioUseCase.getAllFuncs());
    }
}
