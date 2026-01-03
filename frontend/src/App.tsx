import {
  AppBar,
  Toolbar,
  Typography,
  Box,
  Button,
} from "@mui/material";

export default function App() {
  return (
    <Box sx={{ minHeight: "100vh", bgcolor: "#121212" }}>
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6">Personal App</Typography>
        </Toolbar>
      </AppBar>

      <Box sx={{ p: 2 }}>
        <Button variant="contained" fullWidth>
          Novo Aluno
        </Button>
      </Box>
    </Box>
  );
}
