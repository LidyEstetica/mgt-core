package com.lidy.estetica.usecase;

import com.lidy.estetica.model.Funcionario;
import com.lidy.estetica.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FuncionarioUsecase {

    @Autowired
    private FuncionarioService funcionarioService;

    public List<Funcionario> getAllFuncs() {
        return funcionarioService.getAllFuncs();
    }

}
