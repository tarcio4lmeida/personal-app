package com.example.personal_api.controller;

import com.example.personal_api.dto.AlunoDetalheResponse;
import com.example.personal_api.dto.AlunoListaResponse;
import com.example.personal_api.dto.TreinoAtivoResponse;
import com.example.personal_api.service.AlunoService;
import com.example.personal_api.service.TreinoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/alunos")
public class AlunoController {

    private final TreinoService treinoService;

    private final AlunoService alunoService;

    @GetMapping("/{alunoId}/treino-ativo")
    public AlunoDetalheResponse buscarTreinoAtivo(@PathVariable Long alunoId) {
        return treinoService.buscarTreinoAtivo(alunoId);
    }

    @GetMapping
    public List<AlunoListaResponse> listarAlunos() {
        return alunoService.listarAlunos();
    }

}
