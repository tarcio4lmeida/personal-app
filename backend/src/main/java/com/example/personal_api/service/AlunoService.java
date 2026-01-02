package com.example.personal_api.service;

import com.example.personal_api.dto.AlunoListaResponse;
import com.example.personal_api.entity.AtributosCorporais;
import com.example.personal_api.repository.AlunoRepository;
import com.example.personal_api.repository.AtributosCorporaisRepository;
import com.example.personal_api.service.exception.AlunoNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final AtributosCorporaisRepository atributosRepository;

    public List<AlunoListaResponse> listarAlunos() {
        return alunoRepository.findAll().stream()
                .map(aluno -> new AlunoListaResponse(
                        aluno.getId(),
                        aluno.getNome(),
                        aluno.getObjetivo(),
                        aluno.getPesoAtual(),
                        atributosRepository
                                .findFirstByAlunoIdOrderByDataAvaliacaoDesc(aluno.getId())
                                .map(AtributosCorporais::getDataAvaliacao)
                                .orElseThrow(() -> new AlunoNaoEncontradoException(aluno.getId()))
                ))
                .toList();
    }
}