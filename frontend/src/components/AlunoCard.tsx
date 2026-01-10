import {
  Card,
  CardContent,
  Typography,
  Box,
  Chip,
} from "@mui/material";
import ChevronRightIcon from "@mui/icons-material/ChevronRight";
import type { Aluno } from "../types/aluno";
import { useMemo } from "react";

interface Props {
  aluno: Aluno;
  onClick: () => void;
}

export function AlunoCard({ aluno, onClick }: Props) {
  
  const status = useMemo(() => {
    if (!aluno.dataUltimaAvaliacao) {
      return { label: "Sem avaliação", color: "error" as const };
    }

    const diff = aluno.qtdDiasUltimaAvaliacao;

    if (diff <= 15) return { label: `${diff} dias`, color: "success" as const };
    if (diff <= 45) return { label: `${diff} dias`, color: "warning" as const };
    return { label: `${diff} dias`, color: "error" as const };
  }, [aluno]);

  return (
    <Card
      onClick={onClick}
      sx={{
        mb: 1.5,
        cursor: "pointer",
        transition: "0.2s",
        "&:hover": { boxShadow: 4 },
      }}
    >
      <CardContent>
        <Box
          sx={{
            display: "flex",
            alignItems: "center",
            justifyContent: "space-between",
            gap: 2,
          }}
        >
          {/* ESQUERDA */}
          <Box sx={{ display: "flex", flexDirection: "column", flexGrow: 1 }}>
            <Typography fontWeight={600} sx={{ mb: 0.3 }}>
              {aluno.nome}
            </Typography>

            <Box sx={{ display: "flex", alignItems: "center", gap: 1 }}>
              <Chip
                label={aluno.objetivo}
                size="small"
                variant="outlined"
                color="primary"
              />

              <Chip
                label={status.label}
                size="small"
                color={status.color}
                variant="outlined"
              />
            </Box>
          </Box>

          {/* SETINHA */}
          <ChevronRightIcon color="action" />
        </Box>
      </CardContent>
    </Card>
  );
}
