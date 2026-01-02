package com.example.personal_api.dto;

import java.time.LocalDate;

public record CriarAvaliacaoFisicaRequest(

        LocalDate dataAvaliacao,

        Double peso,
        Double altura,
        Double gorduraCorporal,

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
        Double cintura,
        Double abdomen,
        Double quadril,
        Double coxaDireita,
        Double coxaEsquerda,
        Double panturrilhaDireita,
        Double panturrilhaEsquerda,

        String observacoes
) {}

