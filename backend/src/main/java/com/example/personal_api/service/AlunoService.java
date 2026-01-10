package com.example.personal_api.service;

import com.example.personal_api.dto.AlunoListaResponse;
import com.example.personal_api.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public Page<AlunoListaResponse> listarAlunos(
            Pageable pageable,
            String search
    ) {
        Page<AlunoListaResponse> pageOriginal = alunoRepository
                .listarAlunosComUltimaAvaliacao(1L, pageable, search);

        return pageOriginal.map(aluno -> {
            LocalDate data = aluno.dataUltimaAvaliacao();

            int diffDias = 0;

            if (data != null) {
                diffDias = Period.between(data, LocalDate.now()).getDays();
            }

            return new AlunoListaResponse(
                    aluno.id(),
                    aluno.nome(),
                    aluno.objetivo(),
                    aluno.pesoAtual(),
                    aluno.dataUltimaAvaliacao(),
                    diffDias
            );
        });
    }
}