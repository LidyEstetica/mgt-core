// Cliente.java
package com.lidy.estetica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ManyToMany
    @JoinTable(
            name = "cliente_procedimento",
            joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "procedimento_id")
    )
    private List<Procedimento> procedimentos;

    @ManyToMany
    @JoinTable(
            name = "cliente_funcionario",
            joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "funcionario_id")
    )
    private List<Funcionario> funcionarios;
}