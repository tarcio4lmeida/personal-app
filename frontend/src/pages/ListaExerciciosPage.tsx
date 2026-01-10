import {
  Box,
  Typography,
  Card,
  CardContent,
  IconButton,
} from "@mui/material";
import ArrowBackIcon from "@mui/icons-material/ArrowBack";
import { useLocation, useNavigate } from "react-router-dom";

interface Exercicio {
  id: number;
  nome: string;
  series: number;
  repeticoes: number;
  observacoes?: string | null;
}

interface LocationState {
  titulo: string;
  exercicios: Exercicio[];
}

export function ListaExerciciosPage() {
  const navigate = useNavigate();
  const location = useLocation();
  const state = location.state as LocationState | null;

  if (!state) {
    return (
      <Box sx={{ p: 2 }}>
        <Typography>Divisão não encontrada</Typography>
      </Box>
    );
  }

  const { titulo, exercicios } = state;

  return (
    <Box sx={{ p: 2, maxWidth: 600, mx: "auto" }}>
      {/* HEADER */}
      <Box
        sx={{
          display: "flex",
          alignItems: "center",
          gap: 1,
          mb: 2,
        }}
      >
        <Typography variant="h6" fontWeight={600}>
          {titulo}
        </Typography>
      </Box>

      {/* LISTA DE EXERCÍCIOS */}
      {exercicios.map((ex) => (
        <Card
          key={ex.id}
          sx={{
            mb: 1.5,
            cursor: "pointer",
            transition: "0.2s",
            "&:hover": { boxShadow: 4 },
          }}
          onClick={() =>
            navigate(`/exercicios/${ex.id}`, {
              state: ex,
            })
          }
        >
          <CardContent>
            <Typography fontWeight={600}>
              {ex.nome}
            </Typography>

            <Typography variant="body2" color="text.secondary">
              {ex.series} x {ex.repeticoes}
            </Typography>

            {ex.observacoes && (
              <Typography
                variant="caption"
                color="text.secondary"
              >
                {ex.observacoes}
              </Typography>
            )}
          </CardContent>
        </Card>
      ))}
    </Box>
  );
}
