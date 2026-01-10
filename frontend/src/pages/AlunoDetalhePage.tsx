import {
  Box,
  Typography,
  CircularProgress,
  Divider,
  Card,
  CardContent,
  Accordion,
  AccordionSummary,
  AccordionDetails,
  Chip,
} from "@mui/material";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
import { useParams, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { buscarAlunoDetalhe } from "../services/alunosService";
import { listarAvaliacoesDoAluno } from "../services/avaliacoesService";
import type { AlunoDetalheResponse } from "../types/alunoDetalhe";
import type { AvaliacaoResumo } from "../types/avaliacaoResumo";

export function AlunoDetalhePage() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [aluno, setAluno] = useState<AlunoDetalheResponse | null>(null);
  const [avaliacoes, setAvaliacoes] = useState<AvaliacaoResumo[]>([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if (!id) return;

    Promise.all([
      buscarAlunoDetalhe(Number(id)),
      listarAvaliacoesDoAluno(Number(id)),
    ])
      .then(([alunoResp, avaliacoesResp]) => {
        setAluno(alunoResp);
        // garante ordem: mais recente primeiro
        setAvaliacoes(
          [...avaliacoesResp].sort(
            (a, b) =>
              new Date(b.dataAvaliacao).getTime() -
              new Date(a.dataAvaliacao).getTime()
          )
        );
      })
      .finally(() => setLoading(false));
  }, [id]);

  if (loading) {
    return (
      <Box sx={{ p: 2, maxWidth: 600, mx: "auto" }}>
        <CircularProgress />
      </Box>
    );
  }

  if (!aluno) {
    return (
      <Box sx={{ p: 2, maxWidth: 600, mx: "auto" }}>
        <Typography color="error">Aluno não encontrado</Typography>
      </Box>
    );
  }

  const temDivisoes =
    aluno.treinoAtivo &&
    Array.isArray(aluno.treinoAtivo.divisoes) &&
    aluno.treinoAtivo.divisoes.length > 0;

  return (
    <Box sx={{ p: 2, maxWidth: 600, mx: "auto" }}>
      {/* ALUNO */}
      <Card sx={{ mb: 3 }}>
        <CardContent>
          <Typography variant="h6" fontWeight={600}>
            {aluno.nome}
          </Typography>

          <Typography variant="body2" color="text.secondary">
            Objetivo: {aluno.objetivo}
          </Typography>

          <Typography sx={{ mt: 1 }}>
            Peso atual: {aluno.pesoAtual} kg
          </Typography>
        </CardContent>
      </Card>

      {/* TREINO ATIVO */}
      <Typography variant="h6" fontWeight={600} sx={{ mb: 1 }}>
        Treino ativo
      </Typography>

      {!aluno.treinoAtivo && (
        <Typography color="text.secondary">
          Nenhum treino ativo
        </Typography>
      )}

      {aluno.treinoAtivo && (
        <>
          <Typography variant="body2" color="text.secondary" sx={{ mb: 2 }}>
            {aluno.treinoAtivo.nome}
          </Typography>

          {aluno.treinoAtivo && temDivisoes && (
          <Card
            sx={{
              mb: 2,
              borderLeft: "4px solid",
              borderColor: "primary.main",
              p: 1,
            }}
          >
            {aluno.treinoAtivo.divisoes
              .slice()
              .sort((a, b) => a.nome.localeCompare(b.nome))
              .map((divisao, index) => (
                <Box
                  key={divisao.id}
                  sx={{
                    p: 1.5,
                    cursor: "pointer",
                    borderBottom:
                      index !== aluno.treinoAtivo.divisoes.length - 1
                        ? "1px solid #ddd"
                        : "none",
                    "&:hover": {
                      backgroundColor: "rgba(0,0,0,0.04)",
                    },
                  }}
                  onClick={() =>
                    navigate("/exercicios", {
                      state: {
                        titulo: `Treino ${divisao.nome} — ${divisao.grupoMuscular}`,
                        exercicios: divisao.exercicios,
                      },
                    })
                  }
                >
                  <Typography fontWeight={600}>
                    Treino {divisao.nome} — {divisao.grupoMuscular}
                  </Typography>
                </Box>
              ))}
          </Card>
        )}


          {!temDivisoes && (
            <Typography color="text.secondary">
              Treino sem divisões cadastradas
            </Typography>
          )}
        </>
      )}

      <Divider sx={{ my: 3 }} />

      {/* AVALIAÇÕES */}
      <Box sx={{ mb: 3 }}>
        {/* TÍTULO */}
        <Box
          sx={{
            display: "flex",
            alignItems: "center",
            gap: 1,
            mb: 1,
          }}
        >
          <Typography variant="h6" fontWeight={600}>
            Avaliações
          </Typography>

          <Chip
            label={avaliacoes.length}
            size="small"
            color="primary"
          />
        </Box>

        {avaliacoes.length === 0 && (
          <Typography color="text.secondary">
            Nenhuma avaliação registrada
          </Typography>
        )}

        {/* ÚLTIMA AVALIAÇÃO */}
        {avaliacoes.length >= 1 && (
          <Card
            sx={{
              mb: 1.5,
              borderLeft: "4px solid",
              borderColor: "primary.main",
              cursor: "pointer",
            }}
            onClick={() => navigate(`/avaliacoes/${avaliacoes[0].id}`)}
          >
            <CardContent>
              <Typography fontWeight={600}>
                Última avaliação
              </Typography>

              <Typography variant="body2" color="text.secondary">
                {avaliacoes[0].nome}
              </Typography>

              <Typography variant="body2">
                {avaliacoes[0].dataAvaliacao
                  ? new Date(
                      avaliacoes[0].dataAvaliacao
                    ).toLocaleDateString("pt-BR")
                  : "-"}
              </Typography>
            </CardContent>
          </Card>
        )}

        {/* HISTÓRICO */}
        {avaliacoes.length > 1 && (
          <Accordion
            sx={{
              boxShadow: "none",
              "&:before": { display: "none" },
            }}
          >
            <AccordionSummary expandIcon={<ExpandMoreIcon />}>
              <Typography fontWeight={500}>
                Avaliações anteriores • {avaliacoes.length - 1}
              </Typography>
            </AccordionSummary>

            <AccordionDetails>
              {avaliacoes.slice(1).map((av) => (
                <Box
                  key={av.id}
                  sx={{
                    py: 1,
                    cursor: "pointer",
                  }}
                  onClick={() => navigate(`/avaliacoes/${av.id}`)}
                >
                  <Typography fontWeight={500}>
                    {av.nome}
                  </Typography>

                  <Typography
                    variant="body2"
                    color="text.secondary"
                  >
                    {av.dataAvaliacao
                      ? new Date(
                          av.dataAvaliacao
                        ).toLocaleDateString("pt-BR")
                      : "-"}
                  </Typography>

                  <Divider sx={{ mt: 1 }} />
                </Box>
              ))}
            </AccordionDetails>
          </Accordion>
        )}
      </Box>
    </Box>
  );
}
