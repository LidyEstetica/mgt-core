package com.lidy.estetica.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClienteDTO {

    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String endereco;
    private String cidade;
    private String estado;
    private String cep;
    private String dataNascimento;
    private String sexo;
    private String observacoes;

}
