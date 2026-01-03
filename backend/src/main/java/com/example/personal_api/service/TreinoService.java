package com.example.personal_api.service;

import com.example.personal_api.dto.AlunoDetalheResponse;
import com.example.personal_api.dto.DivisaoResponse;
import com.example.personal_api.dto.ExercicioResponse;
import com.example.personal_api.dto.TreinoAtivoResponse;
import com.example.personal_api.entity.AtributosCorporais;
import com.example.personal_api.entity.Treino;
import com.example.personal_api.repository.AtributosCorporaisRepository;
import com.example.personal_api.repository.TreinoRepository;
import com.example.personal_api.service.exception.TreinoAtivoNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TreinoService {

    private final TreinoRepository treinoRepository;

    private final AtributosCorporaisRepository atributosRepository;

    public AlunoDetalheResponse buscarTreinoAtivo(Long alunoId) {
        Treino treino = treinoRepository
                .findTreinoAtivoCompletoByAlunoId(alunoId)
                .orElseThrow(() -> new TreinoAtivoNaoEncontradoException(alunoId));


        TreinoAtivoResponse treinoAtivoResponse = new TreinoAtivoResponse(
                treino.getId(),
                treino.getNome(),
                treino.getDescricao(),
                treino.getDivisoes().stream().map(divisao ->
                        new DivisaoResponse(
                                divisao.getId(),
                                divisao.getNome(),
                                divisao.getGrupoMuscular(),
                                divisao.getDivisaoExercicios().stream().map(de ->
                                        new ExercicioResponse(
                                                de.getExercicio().getId(),
                                                de.getExercicio().getNome(),
                                                de.getSeries(),
                                                de.getRepeticoes(),
                                                de.getObservacoes()
                                        )
                                ).toList()
                        )
                ).toList()
        );

        AtributosCorporais ultima =
                atributosRepository
                        .findFirstByAlunoIdOrderByDataAvaliacaoDesc(alunoId)
                        .orElse(null);

        LocalDate dataUltima = ultima != null
                ? ultima.getDataAvaliacao()
                : null;

        Long diasDesdeUltima = dataUltima != null
                ? LocalDate.now().toEpochDay() - dataUltima.toEpochDay()
                : null;

        return new AlunoDetalheResponse(
                treino.getAluno().getId(),
                treino.getAluno().getNome(),
                treino.getAluno().getIdade(),
                treino.getAluno().getPesoAtual(),
                treino.getAluno().getAltura(),
                treino.getAluno().getObjetivo(),
                dataUltima,
                diasDesdeUltima,
                treinoAtivoResponse
        );
    }
}
