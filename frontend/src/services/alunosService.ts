import { api } from "./api";
import type { AlunoDetalheResponse } from "../types/alunoDetalhe";

export async function listarAlunos(
  page = 0,
  size = 10,
  search = ""
) {
  const response = await api.get("/alunos", {
    params: {
      page,
      size,
      search: search || undefined,
    },
  });

  return response.data;
}
export async function buscarAlunoDetalhe(id: number): Promise<AlunoDetalheResponse> {
  const response = await api.get(`/alunos/${id}/treino-ativo`);
  return response.data;
}

