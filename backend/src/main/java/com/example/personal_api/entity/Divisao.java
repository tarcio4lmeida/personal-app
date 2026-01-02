package com.example.personal_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToMany(mappedBy = "divisao", cascade = CascadeType.ALL)
    private Set<DivisaoExercicio> divisaoExercicios = new HashSet<>();

}
