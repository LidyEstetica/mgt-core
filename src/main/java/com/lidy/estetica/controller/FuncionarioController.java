package com.lidy.estetica.controller;

import com.lidy.estetica.model.Funcionario;
import com.lidy.estetica.usecase.FuncionarioUsecase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioUsecase funcionarioUsecase;

    @GetMapping
    public ResponseEntity<List<Funcionario>> getAllFuncs() {
        return ResponseEntity.status(HttpStatus.OK).body(funcionarioUsecase.getAllFuncs());
    }

}
