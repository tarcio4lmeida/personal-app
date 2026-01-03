package com.example.personal_api.controller;

import com.example.personal_api.dto.AvaliacaoFisicaDetalheResponse;
import com.example.personal_api.dto.AvaliacaoFisicaListaResponse;
import com.example.personal_api.dto.CriarAvaliacaoFisicaRequest;
import com.example.personal_api.service.AvaliacaoFisicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/avaliacoes")
public class AvaliacaoFisicaController {

    private final AvaliacaoFisicaService service;

    // ======================
    // DETALHE DA AVALIAÇÃO
    // GET /avaliacoes/{avaliacaoId}
    // ======================
    @GetMapping("/{avaliacaoId}")
    public AvaliacaoFisicaDetalheResponse buscarDetalhe(
            @PathVariable Long avaliacaoId
    ) {
        return service.buscarDetalhe(avaliacaoId);
    }

    // ======================
    // LISTAR AVALIAÇÕES DO ALUNO
    // GET /avaliacoes/aluno/{alunoId}
    // ======================
    @GetMapping("/aluno/{alunoId}")
    public List<AvaliacaoFisicaListaResponse> listar(
            @PathVariable Long alunoId
    ) {
        return service.listarAvaliacoes(alunoId);
    }

    // ======================
    // CRIAR AVALIAÇÃO
    // POST /avaliacoes/aluno/{alunoId}
    // ======================
    @PostMapping("/aluno/{alunoId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void criar(
            @PathVariable Long alunoId,
            @RequestBody CriarAvaliacaoFisicaRequest request
    ) {
        service.criarAvaliacao(alunoId, request);
    }
}
