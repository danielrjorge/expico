import * as React from "react";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Button from "@mui/material/Button";
import { useLanguage } from "../contexts/LanguageContext";
import { Link } from "react-router-dom";

import Paper from "@mui/material/Paper";
import { Box } from "@mui/material";
import DeleteBovineAlertDialog from "./DeleteBovineAlertDialog";

export default function BovineList({ bovineDataArray }) {
  const { translations } = useLanguage();

  const setUpdateData = (data) => {
    localStorage.setItem("updateBovineCode", data.bovineCode);
    localStorage.setItem("updateBovineBreed", data.bovineBreed);
    localStorage.setItem("updateBovineGender", data.bovineGender);
    localStorage.setItem("updateBovineColor", data.bovineColor);
    localStorage.setItem("updateBovineBirthDate", data.bovineBirthDate);
    localStorage.setItem("updateBovineStatus", data.bovineStatus);
    localStorage.setItem("updateBovineName", data.bovineName);
    localStorage.setItem("updateBovineMothersCode", data.mothersCode);
    localStorage.setItem("updateBovineFathersCode", data.fathersCode);
    localStorage.setItem(
      "updateBovineLastOwnerNif",
      data.lastKnownOwner.ownerNIF
    );
  };

  function handleIncomingValues(value) {
    switch (value) {
      // gender
      case "MACHO":
        return translations.male;
      case "FEMEA":
        return translations.female;
      // breed
      case "ANGUS":
        return translations.angus;
      case "CRUZADO_ANGUS":
        return translations.angusMix;
      case "CHAROLES":
        return translations.charolais;
      // color
      case "PRETO":
        return translations.black;
      case "BRANCO":
        return translations.white;
      case "VERMELHO":
        return translations.red;
      default:
        return value;
    }
  }

  function getAge(birthDate) {
    let todate = new Date();

    var age = [],
      fromdate = new Date(birthDate),
      y = [todate.getFullYear(), fromdate.getFullYear()],
      ydiff = y[0] - y[1],
      m = [todate.getMonth(), fromdate.getMonth()],
      mdiff = m[0] - m[1],
      d = [todate.getDate(), fromdate.getDate()],
      ddiff = d[0] - d[1];

    if (mdiff < 0 || (mdiff === 0 && ddiff < 0)) --ydiff;
    if (mdiff < 0) mdiff += 12;
    if (ddiff < 0) {
      fromdate.setMonth(m[1] + 1, 0);
      ddiff = fromdate.getDate() - d[1] + d[0];
      --mdiff;
    }
    if (ydiff > 0) age.push(ydiff + " Y - ");
    if (mdiff > 0) age.push(mdiff + " M - ");
    if (ddiff > 0) age.push(ddiff + " D");
    return age.join("");
  }

  return (
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 650 }} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>{translations.bovineCode}</TableCell>
            <TableCell align="right">{translations.bovineName}</TableCell>
            <TableCell align="right">{translations.bovineBreed}</TableCell>
            <TableCell align="right">{translations.bovineGender}</TableCell>
            <TableCell align="right">{translations.bovineColor}</TableCell>
            <TableCell align="right">{translations.bovineBirthDate}</TableCell>
            <TableCell align="right">{translations.age}</TableCell>
            <TableCell align="right">{translations.mothersCode}</TableCell>
            <TableCell align="right">{translations.fathersCode}</TableCell>
            <TableCell align="right">{translations.owner}</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {bovineDataArray.map((row) => (
            <TableRow
              key={row.bovineInternalId}
              sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
            >
              <TableCell component="th" scope="row">
                {row.bovineCode}
              </TableCell>
              <TableCell align="right">{row.bovineName}</TableCell>
              <TableCell align="right">
                {handleIncomingValues(row.bovineBreed)}
              </TableCell>
              <TableCell align="right">
                {handleIncomingValues(row.bovineGender)}
              </TableCell>
              <TableCell align="right">
                {handleIncomingValues(row.bovineColor)}
              </TableCell>
              <TableCell align="right">{row.bovineBirthDate}</TableCell>
              <TableCell align="right">{getAge(row.bovineBirthDate)}</TableCell>
              <TableCell align="right">{row.mothersCode}</TableCell>
              <TableCell align="right">{row.fathersCode}</TableCell>
              <TableCell align="right">
                {row.lastKnownOwner.ownerName}
              </TableCell>
              <Box
                sx={{
                  display: "flex",
                  flexDirection: "column",
                  justifyContent: "space-between",
                }}
              >
                <Link to="/update-bovine">
                  <Button
                    sx={{ marginTop: 1, marginBottom: 1 }}
                    variant="contained"
                    color="info"
                    onClick={() => setUpdateData(row)}
                  >
                    {translations.update}
                  </Button>
                </Link>
                <DeleteBovineAlertDialog bovineCode={row.bovineCode} />
              </Box>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}
