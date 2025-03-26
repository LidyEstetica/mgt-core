package com.lidy.estetica.service;

import com.lidy.estetica.model.Servico;
import com.lidy.estetica.repository.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicoService {

    private final ServicoRepository servicoRepository;

    public List<Servico> getAllServicos() {
        return servicoRepository.findAll();
    }
} 