import React, { useState, useEffect } from "react";
import {
    Container,
    TextField,
    Button,
    Box,
    Typography,
    Alert,
    MenuItem,
  } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { updateBovine } from "../services/api/api";
import { useLanguage } from "../contexts/LanguageContext";
import dayjs from 'dayjs';

export default function UpdateBovine() {
  const navigate = useNavigate();
  const { translations } = useLanguage();

  useEffect(() => {
    //console.log(localStorage)
    setBovineCode(localStorage.getItem('updateBovineCode'))
    setBovineBreed(localStorage.getItem('updateBovineBreed'))
    setBovineColor(localStorage.getItem('updateBovineColor'))
    setBovineGender(localStorage.getItem('updateBovineGender'))
    setBovineBirthDate(localStorage.getItem('updateBovineBirthDate'))
    setBovineStatus(localStorage.getItem('updateBovineStatus'))
    if(localStorage.getItem('updateBovineName') !== "null") {
        setBovineName(localStorage.getItem('updateBovineName'))
    }
    if(localStorage.getItem('updateBovineMothersCode') !== "null"){
        setMothersCode(localStorage.getItem('updateBovineMothersCode'))
    }
    if(localStorage.getItem('updateBovineFathersCode') !== "null"){
        setFathersCode(localStorage.getItem('updateBovineFathersCode'))
    }
    setLastKnownOwnerNif(localStorage.getItem('updateBovineLastOwnerNif'))
    }, []);

  const [bovineCode, setBovineCode] = useState('');
  const [bovineBreed, setBovineBreed] = useState('');
  const [bovineColor, setBovineColor] = useState('');
  const [bovineGender, setBovineGender] = useState('');
  const [bovineBirthDate, setBovineBirthDate] = useState('2020-02-02');
  const [bovineStatus, setBovineStatus] = useState('');
  const [bovineName, setBovineName] = useState('');
  const [mothersCode, setMothersCode] = useState('');
  const [fathersCode, setFathersCode] = useState('');
  const [lastKnownOwnerNif, setLastKnownOwnerNif] = useState('');

  const [errorDate, setErrorDate] = useState(false);

  const [alert, setAlert] = useState({ type: "", message: "" });

  // it's using these values as ENUM in the back-end
  // later we should put this in a separate file for reusability, but be careful with the translations
  const genders = [
    {
      value: "MACHO",
      label: translations.male,
    },
    {
      value: "FEMEA",
      label: translations.female,
    },
  ];

  const breeds = [
    {
      value: "ANGUS",
      label: translations.angus,
    },
    {
      value: "CRUZADO_ANGUS",
      label: translations.angusMix,
    },
    {
      value: "CHAROLES",
      label: translations.charolais,
    },
  ];

  const colors = [
    {
      value: "PRETO",
      label: translations.black,
    },
    {
      value: "BRANCO",
      label: translations.white,
    },
    {
      value: "VERMELHO",
      label: translations.red,
    },
  ];

  const status = [
    {
      value: "VIVO",
      label: translations.alive,
    },
    {
      value: "MORTO",
      label: translations.dead,
    },
    {
      value: "ABATIDO",
      label: translations.butchered,
    },
    {
      value: "VENDIDO",
      label: translations.sold,
    },
  ];

  const today = new Date();

  const handleErrorDate = () => {
    setErrorDate(new Date(bovineBirthDate) >= today);
  }

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const data = {
        'bovineCode': bovineCode,
        'bovineBreed': bovineBreed,
        'bovineColor': bovineColor,
        'bovineGender': bovineGender,
        'bovineBirthDate': bovineBirthDate,
        'bovineStatus': bovineStatus,
        'bovineName': bovineName,
        'mothersCode': mothersCode,
        'fathersCode': fathersCode,
        'lastKnownOwnerNif': lastKnownOwnerNif,
      };

      if (errorDate) {
        setAlert({
          type: "warning",
          message: "Birth date is incorrect!",
        });
        return;
      }

      await updateBovine(data);
      setAlert({
        type: "success",
        message: translations.bovineUpdatedSuccessfully,
      });
      setTimeout(() => navigate("/get-all-bovines"), 2000); // Navigate back to home after 2 seconds
    } catch (error) {
      setAlert({ type: "error", message: translations.failedToUpdateBovine });
    }
  };

  return (
    <Container>
      <Box sx={{ mt: 3 }}>
        <Typography variant="h4" gutterBottom>
          {translations.updateBovine}
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
              label={translations.bovineCode}
              name={translations.bovineCode}
              value={bovineCode}
              onChange={(e) => {
                setBovineCode(e.target.value);
              }}
              required
              fullWidth
              sx={{ mb: 2 }}
            />
            <TextField
              label={translations.bovineBreed}
              name={translations.bovineBreed}
              value={bovineBreed}
              select
              onChange={(e) => {
                setBovineBreed(e.target.value);
              }}
              fullWidth
              required
              sx={{ mb: 2 }}
            >
              {breeds.map((option) => (
                <MenuItem key={option.value} value={option.value}>
                  {option.label}
                </MenuItem>
              ))}
            </TextField>
          </Box>

          <Box
            sx={{
              display: "flex",
              alignItems: "center",
              "& > :not(style)": { m: 1 },
            }}
          >
            <TextField
              label={translations.bovineGender}
              name={translations.bovineGender}
              value={bovineGender}
              select
              onChange={(e) => {
                setBovineGender(e.target.value);
              }}
              fullWidth
              required
              sx={{ mb: 2 }}
            >
              {genders.map((option) => (
                <MenuItem key={option.value} value={option.value}>
                  {option.label}
                </MenuItem>
              ))}
            </TextField>

            <TextField
              label={translations.bovineColor}
              name={translations.bovineColor}
              value={bovineColor}
              select
              onChange={(e) => {
                setBovineColor(e.target.value);
              }}
              fullWidth
              required
              sx={{ mb: 2 }}
            >
              {colors.map((option) => (
                <MenuItem key={option.value} value={option.value}>
                  {option.label}
                </MenuItem>
              ))}
            </TextField>
          </Box>

          <Box
            sx={{
              display: "flex",
              alignItems: "center",
              "& > :not(style)": { m: 1 },
            }}
          >
            <TextField
              label={translations.bovineBirthDate}
              name={translations.bovineBirthDate}
              value={dayjs(bovineBirthDate).format("YYYY-MM-DD")}
              onChange={(e) => {
                setBovineBirthDate(e.target.value);
                handleErrorDate();
              }}
              fullWidth
              InputLabelProps={{ shrink: true }}
              required
              error={errorDate}
              helperText={errorDate ? "The date you entered is invalid!" : ""}
              type="date"
              sx={{ mb: 2 }}
            />

            <TextField
              label={translations.bovineName}
              name={translations.bovineName}
              value={bovineName}
              onChange={(e) => {
                setBovineName(e.target.value);
              }}
              fullWidth
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
              label={translations.bovineStatus}
              name={translations.bovineStatus}
              value={bovineStatus}
              select
              onChange={(e) => {
                setBovineStatus(e.target.value);
              }}
              fullWidth
              required
              sx={{ mb: 2 }}
            >
              {status.map((option) => (
                <MenuItem key={option.value} value={option.value}>
                  {option.label}
                </MenuItem>
              ))}
            </TextField>

            <TextField
              label={translations.lastKnownOwnerNif}
              name={translations.lastKnownOwnerNif}
              value={lastKnownOwnerNif}
              onChange={(e) => {
                setLastKnownOwnerNif(e.target.value);
              }}
              type="number"
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
              label={translations.mothersCode}
              name={translations.mothersCode}
              value={mothersCode}
              onChange={(e) => {
                setMothersCode(e.target.value);
              }}
              fullWidth
              sx={{ mb: 2 }}
            />

            <TextField
              label={translations.fathersCode}
              name={translations.fathersCode}
              value={fathersCode}
              onChange={(e) => {
                setFathersCode(e.target.value);
              }}
              fullWidth
              sx={{ mb: 2 }}
            />
          </Box>

          <Box sx={{ display: "flex", justifyContent: "space-between" }}>
            <Button
              variant="contained"
              color="secondary"
              onClick={() => navigate("/get-all-bovines")}
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
}
