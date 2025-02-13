package com.lidy.estetica.usecase;

import com.lidy.estetica.model.Cliente;
import com.lidy.estetica.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClienteUseCase {

    @Autowired
    private ClienteService clienteService;

    public void createCliente(Cliente cliente) {
        clienteService.createCliente(cliente);
    }

    public List<Cliente> getAllClientes() {
        return clienteService.getAllClientes();
    }
}