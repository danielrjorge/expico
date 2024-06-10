import React, { useState } from 'react';
import { Container, Box, Typography, Button } from '@mui/material';
import Sidebar from '../components/Sidebar';
import { useLanguage } from '../contexts/LanguageContext';
import { useNavigate } from 'react-router-dom';
import CssBaseline from "@mui/material/CssBaseline";

const Main = () => {

  const { translations } = useLanguage();
  const [selectedItem, setSelectedItem] = useState('allCattle');
  const navigate = useNavigate();

  const renderButtons = () => {
    switch (selectedItem) {
      case 'allCattle':
        return (
          <Box>
            <Button variant="contained" color="primary" sx={{ marginRight: 2 }} onClick={() => navigate('/add-bovine')}>
              {translations.addBovine}
            </Button>
            <Button variant="contained" color="secondary" onClick={() => navigate('/get-all-bovines')}>
              {translations.allBovines}
            </Button>
          </Box>
        );
      case 'farmCattle':
        return (
          <Box>
            <Button variant="contained" color="primary" sx={{ marginRight: 2 }} onClick={() => alert('Navigate to Add Bovine')}>
              {translations.getAllFarmBovines}
            </Button>
          </Box>
        );
      case 'owners':
        return (
          <Box>
            <Button variant="contained" color="primary" sx={{ marginRight: 2 }} onClick={() => alert('Navigate to Add Owner')}>
              {translations.addOwner}
            </Button>
            <Button variant="contained" color="secondary" onClick={() => alert('Navigate to Get Owners')}>
              {translations.getAllOwners}
            </Button>
          </Box>
        );
      case 'land':
        return (
          <Box>
            <Button variant="contained" color="primary" sx={{ marginRight: 2 }} onClick={() => alert('Navigate to Add Land')}>
              {translations.addLand}
            </Button>
            <Button variant="contained" color="secondary" onClick={() => alert('Navigate to Get Lands')}>
              {translations.getAllLands}
            </Button>
          </Box>
        );
      case 'landMovements':
        return (
          <Box>
            <Button variant="contained" color="primary" sx={{ marginRight: 2 }} onClick={() => alert('Navigate to Move Bovine')}>
              {translations.moveBovine}
            </Button>
            <Button variant="contained" color="secondary" onClick={() => alert('Navigate to Get Movements')}>
              {translations.getAllMovements}
            </Button>
          </Box>
        );
      default:
        return null;
    }
  };

  return (
    <Box sx={{ display: 'flex', flexDirection: 'column', height: '100vh' }}>
      <CssBaseline />
      <Box sx={{ display: 'flex', flex: 1 }}>
        <Sidebar onSelect={setSelectedItem} />
        <Container>
          <Typography variant="h3" gutterBottom>
            {translations[selectedItem]}
          </Typography>
          {renderButtons()}
        </Container>
      </Box>
    </Box>
  );
};

export default Main;
