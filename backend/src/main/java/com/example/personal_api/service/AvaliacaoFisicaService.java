package com.example.personal_api.service;

import com.example.personal_api.dto.AvaliacaoFisicaDetalheResponse;
import com.example.personal_api.dto.AvaliacaoFisicaListaResponse;
import com.example.personal_api.dto.CriarAvaliacaoFisicaRequest;
import com.example.personal_api.entity.Aluno;
import com.example.personal_api.entity.AtributosCorporais;
import com.example.personal_api.entity.Avaliacao;
import com.example.personal_api.entity.Treino;
import com.example.personal_api.repository.AlunoRepository;
import com.example.personal_api.repository.AvaliacaoRepository;
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
    private final AvaliacaoRepository avalicaoRepository;

    @Transactional
    public void criarAvaliacao(Long alunoId, CriarAvaliacaoFisicaRequest request) {

        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new AlunoNaoEncontradoException(alunoId));

        Treino treinoAtivo = treinoRepository
                .findByAlunoIdAndAtivoTrue(alunoId)
                .orElse(null);

        AtributosCorporais atributosCorporais = AtributosCorporais.builder()
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
                .build();

        Avaliacao avaliacao = Avaliacao.builder()
                .dataAvaliacao(LocalDate.now())
                .aluno(aluno)
                .atributosCorporais(atributosCorporais)
                .observacoes(request.observacoes()).
                treino(treinoAtivo).build();

        avalicaoRepository.save(avaliacao);

        // Atualiza estado atual do aluno (cache)
        if (request.peso() != null) {
            aluno.setPesoAtual(request.peso());
        }

        alunoRepository.save(aluno);
    }

    public List<AvaliacaoFisicaListaResponse> listarAvaliacoes(Long alunoId) {

        alunoRepository.findById(alunoId)
                .orElseThrow(() -> new AlunoNaoEncontradoException(alunoId));

        return avalicaoRepository.findByAlunoIdOrderByDataAvaliacaoDesc(alunoId)
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

        Avaliacao avaliacao = avalicaoRepository.findById(avaliacaoId)
                .orElseThrow(() -> new AvaliacaoNaoEncontradaException(avaliacaoId));

        return new AvaliacaoFisicaDetalheResponse(
                avaliacao.getId(),
                avaliacao.getDataAvaliacao(),

                avaliacao.getAtributosCorporais().getAltura(),
                avaliacao.getAtributosCorporais().getImc(),

                avaliacao.getAtributosCorporais().getPeso(),
                avaliacao.getAtributosCorporais().getGorduraCorporal(),
                avaliacao.getAtributosCorporais().getMassaMagra(),
                avaliacao.getAtributosCorporais().getMassaGorda(),

                avaliacao.getAtributosCorporais().getMassaMuscular(),
                avaliacao.getAtributosCorporais().getAguaCorporal(),
                avaliacao.getAtributosCorporais().getGorduraVisceral(),
                avaliacao.getAtributosCorporais().getTaxaMetabolicaBasal(),
                avaliacao.getAtributosCorporais().getIdadeMetabolica(),
                avaliacao.getAtributosCorporais().getMassaOssea(),

                avaliacao.getAtributosCorporais().getBracoDireito(),
                avaliacao.getAtributosCorporais().getBracoEsquerdo(),
                avaliacao.getAtributosCorporais().getAntebracoDireito(),
                avaliacao.getAtributosCorporais().getAntebracoEsquerdo(),
                avaliacao.getAtributosCorporais().getPeito(),
                avaliacao.getAtributosCorporais().getCintura(),
                avaliacao.getAtributosCorporais().getAbdomen(),
                avaliacao.getAtributosCorporais().getQuadril(),
                avaliacao.getAtributosCorporais().getCoxaDireita(),
                avaliacao.getAtributosCorporais().getCoxaEsquerda(),
                avaliacao.getAtributosCorporais().getPanturrilhaDireita(),
                avaliacao.getAtributosCorporais().getPanturrilhaEsquerda(),

                avaliacao.getObservacoes(),

                avaliacao.getAluno().getId(),
                avaliacao.getAluno().getNome(),
                avaliacao.getTreino() != null ? avaliacao.getTreino().getId() : null,
                avaliacao.getTreino() != null ? avaliacao.getTreino().getNome() : null
        );
    }

}
