package com.example.personal_api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "divisao")
public class Divisao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String nome; // A, B, C

    @Column(length = 100)
    private String grupoMuscular;

    @ManyToOne(optional = false)
    @JoinColumn(name = "treino_id")
    private Treino treino;
}
