export interface Exercicio {
  id: number;
  nome: string;
  series: number;
  repeticoes: number;
  observacoes?: string | null;
}

export interface Divisao {
  id: number;
  nome: string;
  grupoMuscular: string;
  exercicios: Exercicio[];
}

export interface Treino {
  id: number;
  nome: string;
  observacoes?: string;
  divisoes: Divisao[];
}
