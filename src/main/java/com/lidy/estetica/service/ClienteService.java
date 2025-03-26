package com.lidy.estetica.service;

import com.lidy.estetica.exception.BusinessException;
import com.lidy.estetica.model.Cliente;
import com.lidy.estetica.repository.AgendamentoRepository;
import com.lidy.estetica.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final AgendamentoRepository agendamentoRepository;

    public Cliente createCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getById(int id) {
        return clienteRepository.findById(id);
    }

    public void deleteCliente(Integer id) {
        if (agendamentoRepository.existsByClienteId(id)) {
            throw new BusinessException("Não é possível excluir o cliente pois existem agendamentos vinculados a ele.");
        }
        clienteRepository.deleteById(id);
    }

    public Cliente updateCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
}