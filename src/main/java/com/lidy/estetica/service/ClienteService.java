package com.lidy.estetica.service;

import com.lidy.estetica.model.Cliente;
import com.lidy.estetica.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public void createCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getById(int id) {
        return clienteRepository.findById(id);
    }
}