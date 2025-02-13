package com.lidy.estetica.usecase;

import com.lidy.estetica.model.Agendamento;
import com.lidy.estetica.service.AgendamentoService;
import com.lidy.estetica.service.ClienteService;
import com.lidy.estetica.service.FuncionarioService;
import com.lidy.estetica.service.ProcedimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.lidy.estetica.model.StatusAtendimento.AGENDADO;

@Component
public class AgendamentoUseCase {

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProcedimentoService procedimentoService;

    @Autowired
    private FuncionarioService funcionarioService;


    public List<Agendamento> getAllAgendamentos() {
        return agendamentoService.getAllAgendamentos();
    }

    public List<Agendamento> getSpecificDate(LocalDate data) {
        return agendamentoService.getSpecificDate(data);
    }

    public boolean createAgendamento(LocalDateTime data, int idProcedimento, int quantidadeProcedimento, int idCliente, int idFuncionario) {
        if (!agendamentoService.existsAgendamento(data, idFuncionario)) {
            var procedimento = procedimentoService.getProcedimentoById(idProcedimento);
            var cliente = clienteService.getById(idCliente);
            var funcionario = funcionarioService.getById(idFuncionario);

            Agendamento agendamento = Agendamento.builder()
                    .cliente(cliente)
                    .procedimento(procedimento)
                    .funcionario(funcionario)
                    .data(data)
                    .status(AGENDADO.getDescricao())
                    .quantidade(quantidadeProcedimento)
                    .build();

            agendamentoService.createAgendamento(agendamento);
            return true;
        }
        return false;
    }
}