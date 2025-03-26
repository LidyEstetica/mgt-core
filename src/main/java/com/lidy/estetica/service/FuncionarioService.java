package com.lidy.estetica.service;

import com.lidy.estetica.model.Funcionario;
import com.lidy.estetica.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public List<Funcionario> getAllFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> getById(int id) {
        return funcionarioRepository.findById(id);
    }

}
