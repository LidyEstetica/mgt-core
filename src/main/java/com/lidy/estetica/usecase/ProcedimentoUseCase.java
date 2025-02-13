package com.lidy.estetica.usecase;

import com.lidy.estetica.model.Procedimento;
import com.lidy.estetica.service.ProcedimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProcedimentoUseCase {

    @Autowired
    private ProcedimentoService procedimentoService;

    public List<Procedimento> getAllProcedimentos() {
        return procedimentoService.getAll();
    }
}