package com.example.personal_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "treino")
public class Treino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @Column(name = "data_inicio", nullable = true)
    private LocalDateTime dataInicio;

    private Boolean ativo;


    @OneToMany(
            mappedBy = "treino",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Divisao> divisoes = new ArrayList<>();
}
