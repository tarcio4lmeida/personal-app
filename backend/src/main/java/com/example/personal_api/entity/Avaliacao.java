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
@Table(name = "avaliacao")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dataAvaliacao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "atributos_corporais_id")
    private AtributosCorporais atributosCorporais;

    @ManyToOne(optional = true)
    @JoinColumn(name = "treino_id")
    private Treino treino;

    // ===== Observações gerais =====
    @Column(columnDefinition = "TEXT")
    private String observacoes;
}
