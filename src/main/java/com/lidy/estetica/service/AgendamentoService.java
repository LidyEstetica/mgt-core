package com.lidy.estetica.service;

import com.lidy.estetica.model.Agendamento;
import com.lidy.estetica.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public void createAgendamento(Agendamento agendamento) {
        agendamentoRepository.save(agendamento);
    }

    public List<Agendamento> getAllAgendamentos() {
        return agendamentoRepository.findAll();
    }

    public List<Agendamento> getSpecificDate(LocalDate data) {
        return agendamentoRepository.findByDate(data);
    }

    public boolean existsAgendamento(LocalDateTime data, int funcionarioId) {
        return !agendamentoRepository.findDisponibility(data, funcionarioId).isEmpty();
    }
}