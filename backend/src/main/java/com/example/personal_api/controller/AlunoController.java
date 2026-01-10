package com.example.personal_api.controller;

import com.example.personal_api.dto.AlunoDetalheResponse;
import com.example.personal_api.dto.AlunoListaResponse;
import com.example.personal_api.service.AlunoService;
import com.example.personal_api.service.TreinoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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
    public Page<AlunoListaResponse> listarAlunos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String search
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return alunoService.listarAlunos(pageable, search);
    }

}
