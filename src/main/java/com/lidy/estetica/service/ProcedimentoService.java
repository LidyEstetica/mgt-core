package com.lidy.estetica.service;

import com.lidy.estetica.model.Procedimento;
import com.lidy.estetica.repository.ProcedimentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProcedimentoService {

    private final ProcedimentoRepository procedimentoRepository;

    public Optional<Procedimento> getProcedimentoById(int id) {
        return procedimentoRepository.findById(id);
    }

    public List<Procedimento> getAll() {
        return procedimentoRepository.findAll();
    }

}