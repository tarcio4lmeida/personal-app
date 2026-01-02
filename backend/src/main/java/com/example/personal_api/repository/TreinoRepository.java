package com.example.personal_api.repository;

import com.example.personal_api.entity.Treino;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TreinoRepository extends JpaRepository<Treino, Long> {

    @Query("""
        SELECT DISTINCT t
        FROM Treino t
        LEFT JOIN FETCH t.divisoes d
        LEFT JOIN FETCH d.divisaoExercicios de
        LEFT JOIN FETCH de.exercicio
        WHERE t.aluno.id = :alunoId
          AND t.ativo = true
    """)
    Optional<Treino> findTreinoAtivoCompletoByAlunoId(@Param("alunoId") Long alunoId);

    Optional<Treino> findByAlunoIdAndAtivoTrue(Long alunoId);
}