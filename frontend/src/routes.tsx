import { Routes, Route, Navigate } from "react-router-dom";
import { AlunosPage } from "./pages/AlunosPage";
import { AlunoDetalhePage } from "./pages/AlunoDetalhePage";
import { AvaliacaoDetalhePage } from "./pages/AvaliacaoDetalhePage";
import { ListaExerciciosPage } from "./pages/ListaExerciciosPage";




export default function AppRoutes() {
  return (
    <Routes>
      <Route path="/" element={<Navigate to="/alunos" />} />
      <Route path="/alunos" element={<AlunosPage />} />
      <Route path="/alunos/:id" element={<AlunoDetalhePage />} />
      <Route path="/avaliacoes/:id" element={<AvaliacaoDetalhePage />} />
      <Route path="/exercicios" element={<ListaExerciciosPage />} />
    </Routes>
  );
}
