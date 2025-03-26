package com.lidy.estetica.controller;

import com.lidy.estetica.model.Servico;
import com.lidy.estetica.usecase.ServicoUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    private static final Logger log = LoggerFactory.getLogger(ServicoController.class);

    @Autowired
    private ServicoUseCase servicoUseCase;

    @GetMapping
    public ResponseEntity<List<Servico>> getAllServicos() {
        log.info("Recebida requisição para buscar todos os serviços");
        return ResponseEntity.ok(servicoUseCase.getAllServicos());
    }
} 