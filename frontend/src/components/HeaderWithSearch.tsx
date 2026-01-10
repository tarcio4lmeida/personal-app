import { AppBar, Box, TextField, Toolbar, Typography } from "@mui/material";
import SearchIcon from "@mui/icons-material/Search";

interface Props {
  search: string;
  setSearch: (value: string) => void;
}

export function HeaderWithSearch({ search, setSearch }: Props) {
  return (
    <AppBar position="sticky" sx={{ backgroundColor: "#1976d2" }}>
      <Toolbar sx={{ display: "flex", justifyContent: "space-between" }}>
        
        <Typography variant="h6" fontWeight={600}>
          Personal App
        </Typography>

        <Box
          sx={{
            backgroundColor: "rgba(255, 255, 255, 0.18)",
            borderRadius: "8px",
            padding: "2px 8px",
            display: "flex",
            alignItems: "center",
            cursor: "pointer",
          }}
        >
          <SearchIcon sx={{ color: "white", opacity: 0.9 }} />
        </Box>

      </Toolbar>
    </AppBar>
  );
}
