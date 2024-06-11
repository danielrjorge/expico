import React from "react";
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import { useLanguage } from "../contexts/LanguageContext";
import { useNavigate } from "react-router-dom";

import { deleteBovine } from "../services/api/api";

export default function DeleteAlertDialog( {bovineCode} ) {
  const [open, setOpen] = React.useState(false);
  const { translations } = useLanguage();
  const navigate = useNavigate();

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleCloseAndDelete = () => {
    handleDelete();
    window.location.reload(false);
    setOpen(false);
  };

  const handleDelete = async () => {
    try {

      await deleteBovine(bovineCode);
      setTimeout(() => navigate("/get-all-bovines"), 2000); // Navigate back to home after 2 seconds
      console.log("Bovine with code " + bovineCode + " deleted succefully!");
    } catch (error) {
      console.log("Failed to delete bovine with code " + bovineCode);
    }
  }

  return (
    <React.Fragment>
      <Button variant="contained" color="error" onClick={handleClickOpen}>
        {translations.delete}
      </Button>
      <Dialog
        open={open}
        onClose={handleClose}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">
          {translations.confirmDeleteBovine}
        </DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-description">
            {translations.thisActionCannotBeUndone}
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>{translations.cancel}</Button>
          <Button onClick={handleCloseAndDelete} autoFocus>
            {translations.delete}
          </Button>
        </DialogActions>
      </Dialog>
    </React.Fragment>
  );
}