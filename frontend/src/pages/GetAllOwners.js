import React, { useState, useEffect } from "react";
import { getAllOwners } from "../services/api/api";
import { useLanguage } from "../contexts/LanguageContext";
import { Box, Typography, Alert, Button, Paper } from "@mui/material";
import { useNavigate } from "react-router-dom";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import { Link } from "react-router-dom";

const GetAllOwners = () => {
  const navigate = useNavigate();
  const { translations } = useLanguage();
  const [alert, setAlert] = useState({ type: "", message: "" });
  const [ownerData, setOwnerData] = useState([]);

  useEffect( () => {
    // GET request using axios with error handling
    getAllOwners()
        .then(response => {setOwnerData(response.data)})
        .catch(error => {
            setAlert({ type: "error", message: translations.failedToAddBovine });
            console.error('There was an error!', error);
        });
    }, [translations.failedToAddBovine]);

  return (
    <Box sx={{ mt: 3 }}>
      <Typography variant="h4" gutterBottom>
        {translations.owners}
      </Typography>
      {alert.message && (
        <Alert severity={alert.type} sx={{ mb: 2 }}>
          {alert.message}
        </Alert>
      )}
      <TableContainer component={Paper}>
      <Table sx={{ minWidth: 650 }} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>{translations.ownerName}</TableCell>
            <TableCell align="right">{translations.ownerGovId}</TableCell>
            <TableCell align="right">{translations.ownerNIF}</TableCell>
            <TableCell align="right">{translations.cellNumber}</TableCell>
            <TableCell align="right">{translations.email}</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {ownerData.map((row) => (
            <TableRow
              key={row.ownerInternalId}
              sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
            >
              <TableCell component="th" scope="row">
                {row.ownerName}
              </TableCell>
              <TableCell align="right">{row.ownerGovId}</TableCell>
              <TableCell align="right">
                {row.ownerNIF}
              </TableCell>
              <TableCell align="right">
                {row.ownerCellNumber}
              </TableCell>
              <TableCell align="right">
                {row.ownerEmail}
              </TableCell>
              <Box
                sx={{
                  display: "flex",
                  flexDirection: "column",
                  justifyContent: "space-between",
                }}
              >
                <Link>
                  <Button
                    sx={{ marginTop: 1, marginBottom: 1 }}
                    variant="contained"
                    color="info"
                    onClick={() => alert('update')}
                  >
                    {translations.update}
                  </Button>
                </Link>
              </Box>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
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

export default GetAllOwners;
