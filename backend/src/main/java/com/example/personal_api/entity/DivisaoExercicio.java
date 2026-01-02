package com.example.personal_api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "divisao_exercicio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DivisaoExercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Quantidade de séries
    @Column(nullable = false)
    private Integer series;

    // Repetições por série
    @Column(nullable = false)
    private Integer repeticoes;

    // Carga em kg (ou unidade livre)
    private Double carga;

    // Observações específicas do exercício naquele treino
    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @ManyToOne(optional = false)
    @JoinColumn(name = "divisao_id")
    private Divisao divisao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "exercicio_id")
    private Exercicio exercicio;
}
