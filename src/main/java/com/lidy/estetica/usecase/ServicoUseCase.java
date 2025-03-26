package com.lidy.estetica.usecase;

import com.lidy.estetica.model.Servico;
import com.lidy.estetica.service.ServicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ServicoUseCase {

    private final ServicoService servicoService;

    public List<Servico> getAllServicos() {
        return servicoService.getAllServicos();
    }
} 