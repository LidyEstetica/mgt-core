package com.lidy.estetica.repository;

import com.lidy.estetica.model.Procedimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcedimentoRepository extends JpaRepository<Procedimento, Integer> {
}