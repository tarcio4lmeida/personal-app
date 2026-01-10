package com.example.personal_api.repository;

import com.example.personal_api.entity.AtributosCorporais;
import com.example.personal_api.entity.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    List<Avaliacao>
    findByAlunoIdOrderByDataAvaliacaoDesc(Long alunoId);

    Optional<Avaliacao>
    findFirstByAlunoIdOrderByDataAvaliacaoDesc(Long alunoId);

}