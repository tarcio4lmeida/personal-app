import { api } from "./api";
import type { AvaliacaoResumo } from "../types/avaliacaoResumo";
import type { AvaliacaoDetalhe } from "../types/avaliacaoDetalhe";

export async function listarAvaliacoesDoAluno(
  alunoId: number
): Promise<AvaliacaoResumo[]> {
  const response = await api.get(`/avaliacoes/aluno/${alunoId}`);
  return response.data;
}

export async function buscarAvaliacaoPorId(
  avaliacaoId: number
): Promise<AvaliacaoDetalhe> {
  const response = await api.get(`/avaliacoes/${avaliacaoId}`);
  return response.data;
}
