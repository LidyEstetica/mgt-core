// Procedimento.java
package com.lidy.estetica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Procedimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String duracao;
    private String valor;

    @ManyToMany(mappedBy = "procedimentos")
    private List<Cliente> clientes;
}