package com.example.personal_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "atributos_corporais")
public class AtributosCorporais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ===== Contexto da avaliação =====
    @Column(nullable = false)
    private LocalDate dataAvaliacao;

    // ===== Dados básicos =====
    private Double peso;                 // kg
    private Double altura;               // metros
    private Double imc;                  // pode ser calculado ou informado

    // ===== Composição corporal =====
    private Double gorduraCorporal;      // %
    private Double massaMagra;            // kg
    private Double massaGorda;            // kg

    // ===== Bioimpedância =====
    private Double massaMuscular;
    private Double aguaCorporal;
    private Double gorduraVisceral;
    private Double taxaMetabolicaBasal;
    private Double idadeMetabolica;
    private Double massaOssea;

    // ===== Circunferências (cm) =====
    private Double bracoDireito;
    private Double bracoEsquerdo;
    private Double antebracoEsquerdo;
    private Double antebracoDireito;
    private Double peito;
    private Double cintura;
    private Double abdomen;
    private Double quadril;
    private Double coxaDireita;
    private Double coxaEsquerda;
    private Double panturrilhaEsquerda;
    private Double panturrilhaDireita;

    // ===== Observações gerais =====
    @Column(columnDefinition = "TEXT")
    private String observacoes;

    // ===== Relacionamentos =====

    @ManyToOne(optional = false)
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    /**
     * Treino vigente no momento da avaliação.
     * Pode ser null (ex: avaliação inicial).
     */
    @ManyToOne(optional = true)
    @JoinColumn(name = "treino_id")
    private Treino treino;
}
