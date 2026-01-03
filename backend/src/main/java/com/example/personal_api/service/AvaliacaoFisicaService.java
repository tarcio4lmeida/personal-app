package com.example.personal_api.service;

import com.example.personal_api.dto.AvaliacaoFisicaDetalheResponse;
import com.example.personal_api.dto.AvaliacaoFisicaListaResponse;
import com.example.personal_api.dto.CriarAvaliacaoFisicaRequest;
import com.example.personal_api.entity.Aluno;
import com.example.personal_api.entity.AtributosCorporais;
import com.example.personal_api.entity.Treino;
import com.example.personal_api.repository.AlunoRepository;
import com.example.personal_api.repository.AtributosCorporaisRepository;
import com.example.personal_api.repository.TreinoRepository;
import com.example.personal_api.service.exception.AlunoNaoEncontradoException;
import com.example.personal_api.service.exception.AvaliacaoNaoEncontradaException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliacaoFisicaService {

    private final AlunoRepository alunoRepository;
    private final TreinoRepository treinoRepository;
    private final AtributosCorporaisRepository atributosRepository;

    @Transactional
    public void criarAvaliacao(Long alunoId, CriarAvaliacaoFisicaRequest request) {

        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new AlunoNaoEncontradoException(alunoId));

        Treino treinoAtivo = treinoRepository
                .findByAlunoIdAndAtivoTrue(alunoId)
                .orElse(null);

        AtributosCorporais avaliacao = AtributosCorporais.builder()
                .dataAvaliacao(LocalDate.now())
                .peso(request.peso())
                .altura(request.altura())
                .gorduraCorporal(request.gorduraCorporal())

                // Bioimpedância
                .massaMuscular(request.massaMuscular())
                .aguaCorporal(request.aguaCorporal())
                .gorduraVisceral(request.gorduraVisceral())
                .taxaMetabolicaBasal(request.taxaMetabolicaBasal())
                .idadeMetabolica(request.idadeMetabolica())
                .massaOssea(request.massaOssea())

                // Bioimpedância
                .massaMuscular(request.massaMuscular())
                .aguaCorporal(request.aguaCorporal())
                .gorduraVisceral(request.gorduraVisceral())
                .taxaMetabolicaBasal(request.taxaMetabolicaBasal())
                .idadeMetabolica(request.idadeMetabolica())
                .massaOssea(request.massaOssea())
                .bracoDireito(request.bracoDireito())
                .bracoEsquerdo(request.bracoEsquerdo())
                .antebracoDireito(request.antebracoDireito())
                .antebracoEsquerdo(request.antebracoEsquerdo())
                .cintura(request.cintura())
                .abdomen(request.abdomen())
                .quadril(request.quadril())
                .coxaDireita(request.coxaDireita())
                .coxaEsquerda(request.coxaEsquerda())
                .panturrilhaDireita(request.panturrilhaDireita())
                .panturrilhaEsquerda(request.panturrilhaEsquerda())

                .observacoes(request.observacoes())
                .aluno(aluno)
                .treino(treinoAtivo)
                .build();

        atributosRepository.save(avaliacao);

        // Atualiza estado atual do aluno (cache)
        if (request.peso() != null) {
            aluno.setPesoAtual(request.peso());
        }

        alunoRepository.save(aluno);
    }

    public List<AvaliacaoFisicaListaResponse> listarAvaliacoes(Long alunoId) {

        alunoRepository.findById(alunoId)
                .orElseThrow(() -> new AlunoNaoEncontradoException(alunoId));

        return atributosRepository
                .findByAlunoIdOrderByDataAvaliacaoDesc(alunoId)
                .stream()
                .map(avaliacao -> new AvaliacaoFisicaListaResponse(
                        avaliacao.getId(),
                        avaliacao.getDataAvaliacao(),
                        "Avaliação " + avaliacao.getDataAvaliacao()
                                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                ))
                .toList();
    }

    public AvaliacaoFisicaDetalheResponse buscarDetalhe(Long avaliacaoId) {

        AtributosCorporais a = atributosRepository.findById(avaliacaoId)
                .orElseThrow(() -> new AvaliacaoNaoEncontradaException(avaliacaoId));

        return new AvaliacaoFisicaDetalheResponse(
                a.getId(),
                a.getDataAvaliacao(),

                a.getPeso(),
                a.getAltura(),
                a.getImc(),

                a.getGorduraCorporal(),
                a.getMassaMagra(),
                a.getMassaGorda(),

                a.getMassaMuscular(),
                a.getAguaCorporal(),
                a.getGorduraVisceral(),
                a.getTaxaMetabolicaBasal(),
                a.getIdadeMetabolica(),
                a.getMassaOssea(),

                a.getBracoDireito(),
                a.getBracoEsquerdo(),
                a.getAntebracoDireito(),
                a.getAntebracoEsquerdo(),
                a.getPeito(),
                a.getCintura(),
                a.getAbdomen(),
                a.getQuadril(),
                a.getCoxaDireita(),
                a.getCoxaEsquerda(),
                a.getPanturrilhaDireita(),
                a.getPanturrilhaEsquerda(),

                a.getObservacoes(),

                a.getAluno().getId(),
                a.getAluno().getNome(),
                a.getTreino() != null ? a.getTreino().getId() : null,
                a.getTreino() != null ? a.getTreino().getNome() : null
        );
    }

}
