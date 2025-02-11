package com.lidy.estetica.service;

import com.lidy.estetica.model.Funcionario;
import com.lidy.estetica.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> getAllFuncs() {
        return funcionarioRepository.findAll();
    }

    public Funcionario getById(int id) {
        return funcionarioRepository.getReferenceById(id);
    }

}
