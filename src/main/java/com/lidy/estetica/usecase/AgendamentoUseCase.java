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

    public Page<Agendamento> getAllAgendamentos(Pageable pageable) {
        return agendamentoService.getAllAgendamentos(pageable);
    }

    public List<Agendamento> getSpecificDate(LocalDate data) {
        return agendamentoService.getSpecificDate(data);
    }

    public Optional<Agendamento> getAgendamentoById(Integer id) {
        return agendamentoService.getAgendamentoById(id);
    }

    public void deleteAgendamento(Integer id) {
        agendamentoService.deleteAgendamento(id);
    }

    @Transactional
    public Agendamento createAgendamento(LocalDateTime data, int idProcedimento, 
            int quantidadeProcedimento, int idCliente, int idFuncionario, String observacoes) {
        
        var procedimento = procedimentoService.getProcedimentoById(idProcedimento)
            .orElseThrow(() -> new AgendamentoException("Procedimento não encontrado"));
        
        var cliente = clienteService.getById(idCliente)
            .orElseThrow(() -> new AgendamentoException("Cliente não encontrado"));
        
        var funcionario = funcionarioService.getById(idFuncionario)
            .orElseThrow(() -> new AgendamentoException("Funcionário não encontrado"));

        // Verifica disponibilidade do horário
        if (agendamentoService.existsAgendamento(data, idFuncionario)) {
            throw new AgendamentoException("Já existe um agendamento para este horário e funcionário");
        }

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
    }

    public List<Agendamento> getAgendamentosByClienteId(Integer clienteId) {
        return agendamentoService.getAgendamentosByClienteId(clienteId);
    }
}