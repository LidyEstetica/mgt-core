package com.lidy.estetica.usecase;

import com.lidy.estetica.exception.BusinessException;
import com.lidy.estetica.model.Agendamento;
import com.lidy.estetica.model.Cliente;
import com.lidy.estetica.service.AgendamentoService;
import com.lidy.estetica.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClienteUseCase {

    private final ClienteService clienteService;
    private final AgendamentoService agendamentoService;

    public List<Cliente> getAllClientes() {
        return clienteService.getAllClientes();
    }

    public Optional<Cliente> getById(Integer id) {
        return clienteService.getById(id);
    }

    public Cliente createCliente(Cliente cliente) {
        return clienteService.createCliente(cliente);
    }

    @Transactional
    public void deleteClienteEmCascata(Integer clienteId) {
        // Busca os agendamentos do cliente antes de deletar
        List<Agendamento> agendamentos = agendamentoService.getAgendamentosByClienteId(clienteId);

        // Deleta os agendamentos
        agendamentos.forEach(agendamento ->
                agendamentoService.deleteAgendamento(agendamento.getId())
        );

        // Deleta o cliente
        clienteService.deleteCliente(clienteId);
    }

    public List<Agendamento> getAgendamentosCliente(Integer clienteId) {
        return agendamentoService.getAgendamentosByClienteId(clienteId);
    }

    public Cliente updateCliente(Cliente cliente) {
        // Verifica se o cliente existe
        clienteService.getById(Math.toIntExact(cliente.getId()))
                .orElseThrow(() -> new BusinessException("Cliente não encontrado"));

        return clienteService.updateCliente(cliente);
    }
}