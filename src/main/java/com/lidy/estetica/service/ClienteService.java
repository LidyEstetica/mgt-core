package com.lidy.estetica.service;

import com.lidy.estetica.model.Cliente;
import com.lidy.estetica.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public void createCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Cliente getById(int id) {
        return clienteRepository.findById(id).orElse(null);
    }
}