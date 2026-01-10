import {
  AppBar,
  Toolbar,
  Typography,
  Box,
  IconButton,
} from "@mui/material";
import ArrowBackIcon from "@mui/icons-material/ArrowBack";
import { useLocation, useNavigate } from "react-router-dom";
import AppRoutes from "./routes";

export default function App() {
  const navigate = useNavigate();
  const location = useLocation();

  // mostra botão de voltar exceto na tela principal
  const mostrarVoltar = location.pathname !== "/alunos";

  return (
    <Box sx={{ minHeight: "100vh", bgcolor: "#ecececda" }}>
      <AppBar position="static">
        <Toolbar>
          {mostrarVoltar && (
            <IconButton
              edge="start"
              color="inherit"
              onClick={() => navigate(-1)}
              sx={{ mr: 1 }}
            >
              <ArrowBackIcon />
            </IconButton>
          )}

          <Typography variant="h6">
            Personal App
          </Typography>
        </Toolbar>
      </AppBar>

      <AppRoutes />
    </Box>
  );
}
