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
(3, 'Rafael Lima', 40, 90.2, 1.80, 'Condicionamento', 'Histórico de dor no joelho', 1),
(4, 'Ana Paula Mendes', 29, 58.4, 1.62, 'Emagrecimento', 'Busca perder gordura localizada', 1),
(5, 'Bruno Azevedo', 35, 88.0, 1.75, 'Hipertrofia', 'Treina há 2 anos', 1),
(6, 'Fernanda Rocha', 41, 70.3, 1.68, 'Qualidade de vida', 'Retornando aos treinos após pausa', 1),
(7, 'Lucas Pereira', 24, 76.8, 1.80, 'Hipertrofia', 'Aluno disciplinado', 1),
(8, 'Juliana Nogueira', 38, 62.5, 1.60, 'Emagrecimento', 'Objetivo estético e saúde', 1),
(9, 'Eduardo Santos', 45, 95.6, 1.82, 'Condicionamento', 'Foco em resistência cardiovascular', 1),
(10, 'Patrícia Almeida', 33, 68.9, 1.70, 'Emagrecimento', 'Treina 4x por semana', 1),
(11, 'Rodrigo Farias', 50, 85.2, 1.74, 'Qualidade de vida', 'Hipertenso controlado', 1),
(12, 'Camila Torres', 27, 59.7, 1.64, 'Hipertrofia', 'Foco em membros inferiores', 1),
(13, 'Marcelo Ribeiro', 42, 92.1, 1.78, 'Emagrecimento', 'Precisa melhorar mobilidade', 1);

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
-- DIVISAO
-- =========================
INSERT INTO divisao (nome, grupo_muscular, treino_id) VALUES
('A', 'Peito e Tríceps', 1),
('B', 'Costas e Bíceps', 1),
('C', 'Pernas', 1),
('A', 'Full Body', 2),
('B', 'Core e Cardio', 2),
('A', 'Inferiores', 3),
('B', 'Superiores', 3),
('A', 'Peito e Tríceps', 4),
('B', 'Costas e Bíceps', 4),
('C', 'Pernas + Core', 4);


-- =========================
-- EXERCÍCIOS (catálogo)
-- =========================
INSERT INTO exercicio (nome, grupo_muscular) VALUES
('Supino Reto', 'Peito'),
('Crucifixo', 'Peito'),
('Puxada Frente', 'Costas'),
('Remada Curvada', 'Costas'),
('Agachamento Livre', 'Pernas'),
('Leg Press', 'Pernas'),
('Rosca Direta', 'Bíceps'),
('Tríceps Pulley', 'Tríceps'),
('Prancha', 'Core'),
('Corrida Esteira', 'Cardio');

-- =========================
-- DIVISAO_EXERCICIO
-- =========================
INSERT INTO divisao_exercicio
(series, repeticoes, carga, observacoes, divisao_id, exercicio_id)
VALUES
-- Carlos - Treino A
(4, 10, NULL, 'Controle de movimento', 1, 1),
(3, 12, NULL, NULL, 1, 2),
(3, 12, NULL, NULL, 1, 8),

-- Carlos - Treino B
(4, 10, NULL, NULL, 2, 3),
(3, 10, NULL, NULL, 2, 4),
(3, 12, NULL, NULL, 2, 7),

-- Carlos - Treino C
(4, 8, NULL, 'Atenção ao joelho', 3, 5),
(3, 12, NULL, NULL, 3, 6),

-- Mariana - treino antigo
(3, 15, NULL, NULL, 4, 10),
(3, 30, NULL, NULL, 4, 9),

-- Mariana - treino atual (evolução)
(4, 12, NULL, 'Controle de carga', 8, 1),
(3, 10, NULL, NULL, 8, 8),
(4, 10, NULL, NULL, 9, 3),
(3, 12, NULL, NULL, 9, 7),
(4, 10, NULL, 'Foco em estabilidade', 10, 5),
(3, 30, NULL, NULL, 10, 9);


-- =========================
-- ATRIBUTOS_CORPORAIS (SEM OBSERVAÇÕES)
-- =========================

INSERT INTO atributos_corporais (
    peso, altura, imc,
    gordura_corporal, massa_magra, massa_gorda,
    braco_direito, braco_esquerdo,
    antebraco_direito, antebraco_esquerdo,
    peito, cintura, abdomen, quadril,
    coxa_direita, coxa_esquerda,
    panturrilha_direita, panturrilha_esquerda
) VALUES

-- 1 - Carlos (Avaliação inicial)
(84.0, 1.78, 26.5,
 22.0, 65.5, 18.5,
 34.0, 33.5,
 29.0, 28.5,
 102.0, 92.0, 94.0, 100.0,
 58.0, 57.5,
 38.0, 37.5),

-- 2 - Carlos (Pós treino)
(82.5, 1.78, 26.0,
 20.5, 66.8, 15.7,
 35.0, 34.5,
 30.0, 29.5,
 104.0, 90.0, 92.0, 99.0,
 59.5, 59.0,
 38.5, 38.0),

-- 3 - Mariana (Inicial)
(69.0, 1.65, 25.3,
 30.0, 48.3, 20.7,
 29.0, 28.5,
 24.0, 23.5,
 90.0, 82.0, 84.0, 98.0,
 56.0, 55.5,
 35.0, 34.5),

-- 4 - Mariana (Durante treino)
(66.5, 1.65, 24.4,
 27.5, 48.5, 18.0,
 29.5, 29.0,
 24.5, 24.0,
 91.0, 79.0, 81.0, 96.0,
 55.0, 54.5,
 35.5, 35.0),

-- 5 - Mariana (Treino avançado)
(64.8, 1.65, 23.8,
 24.0, 49.2, 15.6,
 30.5, 30.0,
 25.0, 24.5,
 92.5, 76.0, 78.0, 94.0,
 54.0, 53.5,
 36.0, 35.5),

-- 6 - Rafael (Condicionamento)
(90.2, 1.80, 27.8,
 26.0, 66.7, 23.5,
 36.0, 35.5,
 31.0, 30.5,
 105.0, 95.0, 97.0, 103.0,
 60.0, 59.5,
 39.0, 38.5);


-- =========================
-- AVALIACOES (AGORA COM OBSERVAÇÕES)
-- =========================

INSERT INTO avaliacao (data_avaliacao, aluno_id, treino_id, atributos_corporais_id, observacoes) VALUES
-- 1 - Carlos inicial
('2024-12-01', 1, NULL, 1, 'Avaliação inicial antes da prescrição do treino.'),

-- 2 - Carlos pós treino
('2025-01-01', 1, 1, 2, 'Boa resposta ao treino. Ganho de massa magra.'),

-- 3 - Mariana inicial
('2024-11-20', 2, NULL, 3, 'Avaliação inicial. Objetivo emagrecimento.'),

-- 4 - Mariana durante treino
('2024-12-30', 2, 2, 4, 'Redução de gordura corporal. Boa aderência.'),

-- 5 - Mariana avançado
('2025-02-10', 2, 4, 5, 'Evolução excelente. Pode intensificar o treino.'),

-- 6 - Rafael condicionamento
('2025-01-05', 3, 3, 6, 'Condicionamento geral. Evitar impacto no joelho.');
