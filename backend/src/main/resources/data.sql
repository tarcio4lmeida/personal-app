-- =========================
-- PERSONAL
-- =========================
INSERT INTO personal (id, nome, email) VALUES
(1, 'João Personal', 'joao@personal.com');

-- =========================
-- ALUNOS
-- =========================
INSERT INTO aluno (id, nome, idade, peso_atual, altura, objetivo, observacoes, personal_id) VALUES
(1, 'Carlos Silva', 28, 82.5, 1.78, 'Hipertrofia', 'Aluno iniciante', 1),
(2, 'Mariana Costa', 32, 65.0, 1.65, 'Emagrecimento', 'Treina 3x por semana', 1),
(3, 'Rafael Lima', 40, 90.2, 1.80, 'Condicionamento', 'Histórico de dor no joelho', 1);

-- =========================
-- TREINOS
-- =========================
INSERT INTO treino (id, nome, ativo, descricao, aluno_id) VALUES
(1, 'Hipertrofia Junho', true,
 'Treino focado em ganho de massa. Priorizar execução correta e descanso adequado.',
 1),

(2, 'Emagrecimento Inicial', false,
 'Treino inicial para adaptação. Ritmo moderado.',
 2),

(3, 'Condicionamento Junho', true,
 'Evitar impacto excessivo devido ao joelho.',
 3),

(4, 'Emagrecimento Avançado', true,
 'Aluno evoluiu bem. Pode intensificar o treino mantendo boa execução.',
 2);


-- =========================
-- DIVISÕES
-- =========================
INSERT INTO divisao (id, nome, grupo_muscular, treino_id) VALUES
-- Carlos
(1, 'A', 'Peito e Tríceps', 1),
(2, 'B', 'Costas e Bíceps', 1),
(3, 'C', 'Pernas', 1),

-- Mariana - Treino antigo (2x por semana)
(4, 'A', 'Full Body', 2),
(5, 'B', 'Core e Cardio', 2),

-- Rafael
(6, 'A', 'Inferiores', 3),
(7, 'B', 'Superiores', 3),

-- Mariana - Treino atual (3x por semana, mais avançado)
(8, 'A', 'Peito e Tríceps', 4),
(9, 'B', 'Costas e Bíceps', 4),
(10, 'C', 'Pernas + Core', 4);

-- =========================
-- EXERCÍCIOS (catálogo)
-- =========================
INSERT INTO exercicio (id, nome, grupo_muscular) VALUES
(1, 'Supino Reto', 'Peito'),
(2, 'Crucifixo', 'Peito'),
(3, 'Puxada Frente', 'Costas'),
(4, 'Remada Curvada', 'Costas'),
(5, 'Agachamento Livre', 'Pernas'),
(6, 'Leg Press', 'Pernas'),
(7, 'Rosca Direta', 'Bíceps'),
(8, 'Tríceps Pulley', 'Tríceps'),
(9, 'Prancha', 'Core'),
(10, 'Corrida Esteira', 'Cardio');

-- =========================
-- DIVISAO_EXERCICIO
-- =========================
INSERT INTO divisao_exercicio
(id, series, repeticoes, carga, observacoes, divisao_id, exercicio_id)
VALUES
-- Carlos - Treino A
(1, 4, 10, NULL, 'Controle de movimento', 1, 1),
(2, 3, 12, NULL, NULL, 1, 2),
(3, 3, 12, NULL, NULL, 1, 8),

-- Carlos - Treino B
(4, 4, 10, NULL, NULL, 2, 3),
(5, 3, 10, NULL, NULL, 2, 4),
(6, 3, 12, NULL, NULL, 2, 7),

-- Carlos - Treino C
(7, 4, 8, NULL, 'Atenção ao joelho', 3, 5),
(8, 3, 12, NULL, NULL, 3, 6),

-- Mariana - treino antigo
(9, 3, 15, NULL, NULL, 4, 10),
(10, 3, 30, NULL, NULL, 4, 9),

-- Mariana - treino atual (evolução)
(11, 4, 12, NULL, 'Controle de carga', 8, 1),
(12, 3, 10, NULL, NULL, 8, 8),
(13, 4, 10, NULL, NULL, 9, 3),
(14, 3, 12, NULL, NULL, 9, 7),
(15, 4, 10, NULL, 'Foco em estabilidade', 10, 5),
(16, 3, 30, NULL, NULL, 10, 9);
