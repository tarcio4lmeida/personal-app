package com.example.personal_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    private Integer idade;

    @Column(name = "peso_atual")
    private Double pesoAtual;

    private Double altura;

    @Column(length = 50)
    private String objetivo;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @ManyToOne(optional = false)
    @JoinColumn(name = "personal_id")
    private Personal personal;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
