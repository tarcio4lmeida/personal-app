export interface ExercicioResponse {
  id: number;
  nome: string;
  series: number;
  repeticoes: number;
  observacoes?: string | null;
}

export interface DivisaoResponse {
  id: number;
  nome: string;
  grupoMuscular: string;
  exercicios: ExercicioResponse[];
}

export interface TreinoAtivoResponse {
  id: number;
  nome: string;
  observacoes?: string | null;
  divisoes: DivisaoResponse[];
}

export interface AlunoDetalheResponse {
  id: number;
  nome: string;
  idade: number;
  pesoAtual: number;
  altura: number;
  objetivo: string;
  dataUltimaAvaliacao: string;
  diasDesdeUltimaAvaliacao: number;
  treinoAtivo?: TreinoAtivoResponse | null;
}
