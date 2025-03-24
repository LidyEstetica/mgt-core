package com.lidy.estetica.usecase;

import com.lidy.estetica.exception.AgendamentoException;
import com.lidy.estetica.model.Agendamento;
import com.lidy.estetica.service.AgendamentoService;
import com.lidy.estetica.service.ClienteService;
import com.lidy.estetica.service.FuncionarioService;
import com.lidy.estetica.service.ProcedimentoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.lidy.estetica.model.StatusAtendimento.AGENDADO;

@Slf4j
@Component
@RequiredArgsConstructor
public class AgendamentoUseCase {

    private final AgendamentoService agendamentoService;
    private final ClienteService clienteService;
    private final ProcedimentoService procedimentoService;
    private final FuncionarioService funcionarioService;

    @Transactional(readOnly = true)
    public Page<Agendamento> getAllAgendamentos(Pageable pageable) {
        log.debug("Buscando todos os agendamentos com paginação");
        return agendamentoService.getAllAgendamentos(pageable);
    }

    @Transactional(readOnly = true)
    public List<Agendamento> getSpecificDate(LocalDate data) {
        log.debug("Buscando agendamentos para a data: {}", data);
        return agendamentoService.getSpecificDate(data);
    }

    @Transactional(readOnly = true)
    public Optional<Agendamento> getAgendamentoById(Integer id) {
        log.debug("Buscando agendamento por ID: {}", id);
        return agendamentoService.getAgendamentoById(id);
    }

    @Transactional
    public void deleteAgendamento(Integer id) {
        log.info("Deletando agendamento com ID: {}", id);
        agendamentoService.deleteAgendamento(id);
    }

    @Transactional
    public Agendamento createAgendamento(LocalDateTime data, int idProcedimento, 
            int quantidadeProcedimento, int idCliente, int idFuncionario, String observacoes) {
        log.info("Iniciando criação de agendamento para cliente: {}", idCliente);
        
        try {
            validateAgendamento(data, idProcedimento, quantidadeProcedimento, idCliente, idFuncionario);
            
            if (agendamentoService.existsAgendamento(data, idFuncionario)) {
                throw new AgendamentoException("Já existe um agendamento para este horário e funcionário");
            }

            var procedimento = procedimentoService.getProcedimentoById(idProcedimento)
                .orElseThrow(() -> new AgendamentoException("Procedimento não encontrado"));
            
            var cliente = clienteService.getById(idCliente)
                .orElseThrow(() -> new AgendamentoException("Cliente não encontrado"));
            
            var funcionario = funcionarioService.getById(idFuncionario)
                .orElseThrow(() -> new AgendamentoException("Funcionário não encontrado"));

            Agendamento agendamento = Agendamento.builder()
                    .cliente(cliente)
                    .procedimento(procedimento)
                    .funcionario(funcionario)
                    .data(data)
                    .status(AGENDADO.getDescricao())
                    .quantidade(quantidadeProcedimento)
                    .observacao(observacoes)
                    .build();

            return agendamentoService.createAgendamento(agendamento);
        } catch (Exception e) {
            log.error("Erro ao criar agendamento: {}", e.getMessage());
            throw new AgendamentoException("Erro ao criar agendamento: " + e.getMessage());
        }
    }

    private void validateAgendamento(LocalDateTime data, int idProcedimento, 
            int quantidadeProcedimento, int idCliente, int idFuncionario) {
        if (data == null || data.isBefore(LocalDateTime.now())) {
            throw new AgendamentoException("Data do agendamento inválida");
        }
        if (idProcedimento <= 0) {
            throw new AgendamentoException("ID do procedimento inválido");
        }
        if (quantidadeProcedimento <= 0) {
            throw new AgendamentoException("Quantidade de procedimentos deve ser maior que zero");
        }
        if (idCliente <= 0) {
            throw new AgendamentoException("ID do cliente inválido");
        }
        if (idFuncionario <= 0) {
            throw new AgendamentoException("ID do funcionário inválido");
        }
    }
}