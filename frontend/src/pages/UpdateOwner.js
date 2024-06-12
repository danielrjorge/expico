import React, { useState, useEffect } from "react";
import {
  Container,
  TextField,
  Button,
  Box,
  Typography,
  Alert,
} from "@mui/material";
import { useNavigate } from "react-router-dom";
import { updateOwner } from "../services/api/api";
import { useLanguage } from "../contexts/LanguageContext";

const UpdateOwner = () => {
  const navigate = useNavigate();
  const { translations } = useLanguage();
  const [ownerGovId, setOwnerGovId] = useState("");
  const [ownerName, setOwnerName] = useState("");
  const [ownerNIF, setOwnerNIF] = useState("");
  const [ownerCellNumber, setOwnerCellNumber] = useState("");
  const [ownerEmail, setOwnerEmail] = useState("");

  const [alert, setAlert] = useState({ type: "", message: "" });

  useEffect(() => {
    setOwnerName(localStorage.getItem('updateOwnerName'));
    setOwnerGovId(localStorage.getItem('updateOwnerGovId'));
    setOwnerNIF(localStorage.getItem('updateOwnerNIF'));
    setOwnerCellNumber(localStorage.getItem('updateOwnerCellNumber'));
    setOwnerEmail(localStorage.getItem('updateOwnerEmail'));
  }, [])

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const data = {
        ownerGovId: ownerGovId,
        ownerName: ownerName,
        ownerNIF: ownerNIF,
        ownerCellNumber: ownerCellNumber,
        ownerEmail: ownerEmail,
      };

      await updateOwner(data);
      setAlert({
        type: "success",
        message: ownerName + translations.ownerUpdated,
      });
      //setTimeout(() => navigate("/"), 2000); // Navigate back to home after 2 seconds
    } catch (error) {
      setAlert({ type: "error", message: translations.failedToUpdateOwner });
    }
  };

  return (
    <Container>
      <Box sx={{ mt: 3 }}>
        <Typography variant="h4" gutterBottom>
          {translations.updateOwner}
        </Typography>
        {alert.message && (
          <Alert severity={alert.type} sx={{ mb: 2 }}>
            {alert.message}
          </Alert>
        )}
        <form onSubmit={handleSubmit}>
          <Box
            sx={{
              display: "flex",
              alignItems: "center",
              "& > :not(style)": { m: 1 },
            }}
          >
            <TextField
              label={translations.ownerGovId}
              name={translations.ownerGovId}
              value={ownerGovId}
              onChange={(e) => {
                setOwnerGovId(e.target.value);
              }}
              required
              fullWidth
              sx={{ mb: 2 }}
            />
            <TextField
              label={translations.ownerName}
              name={translations.ownerName}
              value={ownerName}
              onChange={(e) => {
                setOwnerName(e.target.value);
              }}
              fullWidth
              required
              sx={{ mb: 2 }}
            />
          </Box>

          <Box
            sx={{
              display: "flex",
              alignItems: "center",
              "& > :not(style)": { m: 1 },
            }}
          >
            <TextField
              label={translations.ownerNIF}
              name={translations.ownerNIF}
              value={ownerNIF}
              onChange={(e) => {
                setOwnerNIF(e.target.value);
              }}
              fullWidth
              required
              type="number"
              sx={{ mb: 2 }}
            />

            <TextField
              label={translations.cellNumber}
              name={translations.cellNumber}
              value={ownerCellNumber}
              onChange={(e) => {
                setOwnerCellNumber(e.target.value);
              }}
              fullWidth
              sx={{ mb: 2 }}
            />
            <TextField
              label={translations.email}
              name={translations.email}
              value={ownerEmail}
              onChange={(e) => {
                setOwnerEmail(e.target.value);
              }}
              type="email"
              fullWidth
              sx={{ mb: 2 }}
            />
          </Box>

          <Box sx={{ display: "flex", justifyContent: "space-between" }}>
            <Button
              variant="contained"
              color="secondary"
              onClick={() => navigate("/get-all-owners")}
            >
              {translations.back}
            </Button>
            <Button variant="contained" color="primary" type="submit">
              {translations.submit}
            </Button>
          </Box>
        </form>
      </Box>
    </Container>
  );
};

export default UpdateOwner;
