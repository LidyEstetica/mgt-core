package com.lidy.estetica.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoDTO {

    private int idProcedimento;
    private int quantidadeProcedimento;
    private int idCliente;
    private int idFuncionario;
    private LocalDateTime data;


}
