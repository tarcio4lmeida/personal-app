import {
  Box,
  Typography,
  CircularProgress,
  Card,
  CardContent,
  Divider,
  Accordion,
  AccordionSummary,
  AccordionDetails,
} from "@mui/material";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import { buscarAvaliacaoPorId } from "../services/avaliacoesService";
import type { AvaliacaoDetalhe } from "../types/avaliacaoDetalhe";

export function AvaliacaoDetalhePage() {
  const { id } = useParams();
  const [avaliacao, setAvaliacao] =
    useState<AvaliacaoDetalhe | null>(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if (!id) return;

    buscarAvaliacaoPorId(Number(id))
      .then(setAvaliacao)
      .finally(() => setLoading(false));
  }, [id]);

  if (loading) {
    return (
      <Box sx={{ p: 2, textAlign: "center" }}>
        <CircularProgress />
      </Box>
    );
  }

  if (!avaliacao) {
    return (
      <Box sx={{ p: 2 }}>
        <Typography color="error">
          Avaliação não encontrada
        </Typography>
      </Box>
    );
  }

  return (
    <Box sx={{ p: 2, maxWidth: 600, mx: "auto" }}>
      {/* CABEÇALHO */}
      <Typography variant="h6" fontWeight={600}>
        Avaliação física
      </Typography>

      <Typography color="text.secondary" sx={{ mb: 2 }}>
        {new Date(avaliacao.dataAvaliacao).toLocaleDateString("pt-BR")}
      </Typography>

      {/* DADOS PRINCIPAIS */}
      <Card sx={{ mb: 3 }}>
        <CardContent>
          <InfoLinha label="Peso" valor={avaliacao.peso} sufixo="kg" />
          <InfoLinha label="Altura" valor={avaliacao.altura} sufixo="m" />
          <InfoLinha label="IMC" valor={avaliacao.imc} />
          <InfoLinha
            label="Gordura corporal"
            valor={avaliacao.gorduraCorporal}
            sufixo="%"
          />
          <InfoLinha
            label="Massa muscular"
            valor={avaliacao.massaMuscular}
            sufixo="kg"
          />
        </CardContent>
      </Card>

      {/* COMPOSIÇÃO CORPORAL */}
      <Accordion defaultExpanded>
        <AccordionSummary expandIcon={<ExpandMoreIcon />}>
          <Typography fontWeight={600}>
            Composição corporal
          </Typography>
        </AccordionSummary>

        <AccordionDetails>
          <InfoLinha label="Massa magra" valor={avaliacao.massaMagra} sufixo="kg" />
          <InfoLinha label="Massa gorda" valor={avaliacao.massaGorda} sufixo="kg" />
          <InfoLinha label="Água corporal" valor={avaliacao.aguaCorporal} sufixo="%" />
          <InfoLinha label="Gordura visceral" valor={avaliacao.gorduraVisceral} />
          <InfoLinha
            label="Taxa metabólica basal"
            valor={avaliacao.taxaMetabolicaBasal}
            sufixo="kcal"
          />
          <InfoLinha label="Idade metabólica" valor={avaliacao.idadeMetabolica} />
          <InfoLinha label="Massa óssea" valor={avaliacao.massaOssea} sufixo="kg" />
        </AccordionDetails>
      </Accordion>

      {/* MEDIDAS CORPORAIS */}
      <Accordion>
        <AccordionSummary expandIcon={<ExpandMoreIcon />}>
          <Typography fontWeight={600}>
            Medidas corporais
          </Typography>
        </AccordionSummary>

        <AccordionDetails>
          <InfoLinha label="Braço direito" valor={avaliacao.bracoDireito} sufixo="cm" />
          <InfoLinha label="Braço esquerdo" valor={avaliacao.bracoEsquerdo} sufixo="cm" />
          <InfoLinha
            label="Antebraço direito"
            valor={avaliacao.antebracoDireito}
            sufixo="cm"
          />
          <InfoLinha
            label="Antebraço esquerdo"
            valor={avaliacao.antebracoEsquerdo}
            sufixo="cm"
          />
          <InfoLinha label="Peito" valor={avaliacao.peito} sufixo="cm" />
          <InfoLinha label="Cintura" valor={avaliacao.cintura} sufixo="cm" />
          <InfoLinha label="Abdômen" valor={avaliacao.abdomen} sufixo="cm" />
          <InfoLinha label="Quadril" valor={avaliacao.quadril} sufixo="cm" />
          <InfoLinha label="Coxa direita" valor={avaliacao.coxaDireita} sufixo="cm" />
          <InfoLinha label="Coxa esquerda" valor={avaliacao.coxaEsquerda} sufixo="cm" />
          <InfoLinha
            label="Panturrilha direita"
            valor={avaliacao.panturrilhaDireita}
            sufixo="cm"
          />
          <InfoLinha
            label="Panturrilha esquerda"
            valor={avaliacao.panturrilhaEsquerda}
            sufixo="cm"
          />
        </AccordionDetails>
      </Accordion>

      {/* OBSERVAÇÕES */}
      {avaliacao.observacoes && (
        <Card sx={{ mt: 3 }}>
          <CardContent>
            <Typography fontWeight={600}>
              Observações
            </Typography>

            <Typography variant="body2" sx={{ mt: 1 }}>
              {avaliacao.observacoes}
            </Typography>
          </CardContent>
        </Card>
      )}
    </Box>
  );
}

/* COMPONENTE AUXILIAR */

function InfoLinha({
  label,
  valor,
  sufixo,
}: {
  label: string;
  valor?: number;
  sufixo?: string;
}) {
  return (
    <>
      <Box
        sx={{
          display: "flex",
          justifyContent: "space-between",
          py: 1,
        }}
      >
        <Typography color="text.secondary">
          {label}
        </Typography>

        <Typography fontWeight={600}>
          {valor ?? "-"} {sufixo}
        </Typography>
      </Box>
      <Divider />
    </>
  );
}
