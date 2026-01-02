package com.example.personal_api.repository;

import com.example.personal_api.entity.AtributosCorporais;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AtributosCorporaisRepository
        extends JpaRepository<AtributosCorporais, Long> {

    Optional<AtributosCorporais>
    findFirstByAlunoIdOrderByDataAvaliacaoDesc(Long alunoId);
}