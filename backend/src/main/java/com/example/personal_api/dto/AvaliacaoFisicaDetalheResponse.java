package com.example.personal_api.dto;

import java.time.LocalDate;

public record AvaliacaoFisicaDetalheResponse(

        Long id,
        LocalDate dataAvaliacao,

        // Dados básicos
        Double peso,
        Double altura,
        Double imc,

        // Composição corporal
        Double gorduraCorporal,
        Double massaMagra,
        Double massaGorda,

        // Bioimpedância
        Double massaMuscular,
        Double aguaCorporal,
        Double gorduraVisceral,
        Double taxaMetabolicaBasal,
        Double idadeMetabolica,
        Double massaOssea,

        // Circunferências
        Double bracoDireito,
        Double bracoEsquerdo,
        Double antebracoDireito,
        Double antebracoEsquerdo,
        Double peito,
        Double cintura,
        Double abdomen,
        Double quadril,
        Double coxaDireita,
        Double coxaEsquerda,
        Double panturrilhaDireita,
        Double panturrilhaEsquerda,

        String observacoes,

        // Contexto
        Long alunoId,
        String alunoNome,
        Long treinoId,
        String treinoNome
) {}
