import React from "react";
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import { useLanguage } from "../contexts/LanguageContext";

import { deleteOwnerByNif } from "../services/api/api";

export default function DeleteOwnerAlertDialog( {ownerNIF} ) {
  const [open, setOpen] = React.useState(false);
  const { translations } = useLanguage();

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
      await deleteOwnerByNif(ownerNIF);
      console.log("Owner with NIF " + ownerNIF + " deleted succefully!");
    } catch (error) {
      console.log("Failed to delete owner with NIF " + ownerNIF);
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
          {translations.confirmDeleteOwner + ownerNIF}
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