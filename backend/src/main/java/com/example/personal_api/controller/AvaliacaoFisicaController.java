package com.example.personal_api.controller;

import com.example.personal_api.dto.CriarAvaliacaoFisicaRequest;
import com.example.personal_api.service.AvaliacaoFisicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/alunos/{alunoId}/avaliacoes")
public class AvaliacaoFisicaController {

    private final AvaliacaoFisicaService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criar(
            @PathVariable Long alunoId,
            @RequestBody CriarAvaliacaoFisicaRequest request
    ) {
        service.criarAvaliacao(alunoId, request);
    }
}
