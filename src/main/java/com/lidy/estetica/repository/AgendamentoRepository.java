package com.lidy.estetica.repository;

import com.lidy.estetica.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {
    @Query("SELECT a FROM Agendamento a WHERE a.data BETWEEN :startOfDay AND :endOfDay")
    List<Agendamento> findByDate(@Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);

    default List<Agendamento> findByDate(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);
        return findByDate(startOfDay, endOfDay);
    }

    @Query("SELECT a FROM Agendamento a WHERE a.data = :dateTime AND a.funcionario.id = :idFuncionario")
    List<Agendamento> findDisponibility(@Param("dateTime") LocalDateTime dateTime, int idFuncionario);
}