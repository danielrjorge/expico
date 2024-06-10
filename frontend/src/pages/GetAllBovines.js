import BovineList from "../components/BovineList";
import React, { useState, useEffect } from "react";
import { getAllBovines } from "../services/api/api";
import { useLanguage } from "../contexts/LanguageContext";
import { Box, Typography, Alert, Button } from "@mui/material";
import { useNavigate } from "react-router-dom";

const GetAllBovines = () => {
  const navigate = useNavigate();
  const { translations } = useLanguage();
  const [alert, setAlert] = useState({ type: "", message: "" });
  const [bovineData, setBovineData] = useState([]);

  useEffect( () => {
    // GET request using axios with error handling
    getAllBovines()
        .then(response => {setBovineData(response.data)})
        .catch(error => {
            setAlert({ type: "error", message: translations.failedToAddBovine });
            console.error('There was an error!', error);
        });
    }, [translations.failedToAddBovine]);

  return (
    <Box sx={{ mt: 3 }}>
      <Typography variant="h4" gutterBottom>
        {translations.allBovines}
      </Typography>
      {alert.message && (
        <Alert severity={alert.type} sx={{ mb: 2 }}>
          {alert.message}
        </Alert>
      )}
      <BovineList bovineDataArray={bovineData}/>
      <Box sx={{ display: "flex", justifyContent: "space-between" }}>
        <Button
          variant="contained"
          color="secondary"
          onClick={() => navigate("/")}
        >
          {translations.back}
        </Button>
      </Box>
    </Box>
  );
};

export default GetAllBovines;
