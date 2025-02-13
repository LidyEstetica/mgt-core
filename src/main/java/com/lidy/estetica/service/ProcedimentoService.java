package com.lidy.estetica.service;

import com.lidy.estetica.model.Procedimento;
import com.lidy.estetica.repository.ProcedimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcedimentoService {

    @Autowired
    private ProcedimentoRepository procedimentoRepository;

    public Procedimento getProcedimentoById(int id) {
        return procedimentoRepository.findById(id).orElse(null);
    }

    public List<Procedimento> getAll() {
        return procedimentoRepository.findAll();
    }

}