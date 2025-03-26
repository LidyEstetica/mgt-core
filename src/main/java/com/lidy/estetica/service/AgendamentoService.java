package com.lidy.estetica.service;

import com.lidy.estetica.exception.AgendamentoException;
import com.lidy.estetica.model.Agendamento;
import com.lidy.estetica.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    @Transactional
    @CacheEvict(value = "agendamentos", allEntries = true)
    @Retryable(
            value = {Exception.class},
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000)
    )
    public Agendamento createAgendamento(Agendamento agendamento) {
        log.info("Criando novo agendamento para cliente: {}", agendamento.getCliente().getId());
        validateAgendamento(agendamento);
        try {
            return agendamentoRepository.save(agendamento);
        } catch (Exception e) {
            log.error("Erro ao criar agendamento: {}", e.getMessage());
            throw new AgendamentoException("Erro ao criar agendamento: " + e.getMessage());
        }
    }

    private void validateAgendamento(Agendamento agendamento) {
        if (agendamento.getData() == null || agendamento.getData().isBefore(LocalDateTime.now())) {
            throw new AgendamentoException("Data do agendamento inválida");
        }
        if (agendamento.getProcedimento() == null) {
            throw new AgendamentoException("Procedimento não informado");
        }
        if (agendamento.getQuantidade() <= 0) {
            throw new AgendamentoException("Quantidade de procedimentos deve ser maior que zero");
        }
        if (agendamento.getCliente() == null) {
            throw new AgendamentoException("Cliente não informado");
        }
        if (agendamento.getFuncionario() == null) {
            throw new AgendamentoException("Funcionário não informado");
        }
    }

    @Cacheable(value = "agendamentos", key = "'all'")
    public Page<Agendamento> getAllAgendamentos(Pageable pageable) {
        log.debug("Buscando todos os agendamentos");
        return agendamentoRepository.findAll(pageable);
    }

    @Cacheable(value = "agendamentos", key = "#data")
    public List<Agendamento> getSpecificDate(LocalDate data) {
        log.debug("Buscando agendamentos para a data: {}", data);
        return agendamentoRepository.findByDate(data);
    }

    @Cacheable(value = "agendamentos", key = "'disponibilidade_' + #data + '_' + #funcionarioId")
    public boolean existsAgendamento(LocalDateTime data, int funcionarioId) {
        log.debug("Verificando disponibilidade para data: {} e funcionário: {}", data, funcionarioId);
        return !agendamentoRepository.findDisponibility(data, funcionarioId).isEmpty();
    }

    @Transactional(readOnly = true)
    public Optional<Agendamento> getAgendamentoById(Integer id) {
        log.debug("Buscando agendamento por ID: {}", id);
        return agendamentoRepository.findById(id);
    }

    @Transactional
    @CacheEvict(value = "agendamentos", allEntries = true)
    public void deleteAgendamento(Integer id) {
        log.info("Deletando agendamento com ID: {}", id);
        agendamentoRepository.deleteById(id);
    }

    public List<Agendamento> getAgendamentosByClienteId(Integer clienteId) {
        return agendamentoRepository.findByClienteIdOrderByDataAsc(clienteId);
    }
}