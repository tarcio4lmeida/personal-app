import {
  Box,
  Typography,
  CircularProgress,
  Button,
  TextField,
} from "@mui/material";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { listarAlunos } from "../services/alunosService";
import type { Aluno } from "../types/aluno";
import type { PageResponse } from "../types/pageResponse";
import { AlunoCard } from "../components/AlunoCard";
import { HeaderWithSearch } from "../components/HeaderWithSearch";


export function AlunosPage() {
  const navigate = useNavigate();

  // ESTADOS PRINCIPAIS
  const [alunos, setAlunos] = useState<Aluno[]>([]);
  const [page, setPage] = useState(0);
  const [loading, setLoading] = useState(false);
  const [hasMore, setHasMore] = useState(true);
  const [error, setError] = useState<string | null>(null);

  // ESTADOS DA BUSCA
  const [search, setSearch] = useState("");
  const [debouncedSearch, setDebouncedSearch] = useState("");

  /**
   * ⏳ DEBOUNCE DA BUSCA
   * Aguarda 400ms depois do usuário digitar,
   * e só então atualiza o debouncedSearch.
   * Não reseta a lista aqui para evitar duplicações.
   */
  useEffect(() => {
    const timer = setTimeout(() => {
      setDebouncedSearch(search);
    }, 400);

    return () => clearTimeout(timer);
  }, [search]);

  /**
   * 🚀 AO MONTAR A TELA
   * Quando a tela abre ou o usuário volta para ela,
   * resetamos a lista e carregamos os primeiros registros.
   */
  useEffect(() => {
    resetarLista();
    carregarAlunos();
  }, []); // Rodar apenas 1 vez ao montar

  /**
   * 🔍 QUANDO A BUSCA FINAL MUDA
   * Agora sim resetamos a lista e carregamos a partir do zero.
   */
  useEffect(() => {
    resetarLista();
    carregarAlunos();
  }, [debouncedSearch]);

  /**
   * 🧹 FUNÇÃO PARA RESETAR ESTADO DA LISTA
   */
  function resetarLista() {
    setAlunos([]);
    setPage(0);
    setHasMore(true);
  }

  /**
   * 📡 FUNÇÃO PARA CARREGAR A LISTA DE ALUNOS (PAGINAÇÃO)
   */
  async function carregarAlunos() {
    if (loading || !hasMore) return;

    try {
      setLoading(true);
      setError(null);

      const response: PageResponse<Aluno> =
        await listarAlunos(page, 5, debouncedSearch);

      // Evita duplicação de IDs
      setAlunos((prev) => {
        const novos = [...prev, ...response.content];
        return novos.filter(
          (v, i, arr) => arr.findIndex((x) => x.id === v.id) === i
        );
      });

      setHasMore(!response.last);
      console.log("PAGE", page, "→ trouxe", response.content.length, "alunos", response.content);

      setPage((prev) => prev + 1);
    } catch (err) {
      setError("Erro ao carregar alunos");
    } finally {
      setLoading(false);
    }
  }

  return (
    <>
        <HeaderWithSearch search={search} setSearch={setSearch} />
        <Box sx={{ p: 2, maxWidth: 600, mx: "auto" }}>

          {/* TÍTULO */}
          <Typography variant="h6" fontWeight={600} sx={{ mb: 2 }}>
            Alunos
          </Typography>
          
          {/* ERRO */}
          {error && (
            <Typography color="error" sx={{ mb: 2 }}>
              {error}
            </Typography>
          )}

          {/* LISTA DE ALUNOS */}
          {alunos.map((aluno) => (
            <AlunoCard
              key={aluno.id}
              aluno={aluno}
              onClick={() => navigate(`/alunos/${aluno.id}`)}
            />
          ))}

          {/* LOADING */}
          {loading && (
            <Box sx={{ textAlign: "center", my: 2 }}>
              <CircularProgress size={24} />
            </Box>
          )}

          {/* BOTÃO "CARREGAR MAIS" */}
          {!loading && hasMore && (
            <Box sx={{ textAlign: "center", mt: 2 }}>
              <Button onClick={carregarAlunos}>Carregar mais</Button>
            </Box>
          )}

          {/* LISTA VAZIA */}
          {!loading && alunos.length === 0 && !error && (
            <Typography color="text.secondary">
              Nenhum aluno encontrado
            </Typography>
          )}

        </Box>
      </>
  );
}
