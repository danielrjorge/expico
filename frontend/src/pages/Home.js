import React from 'react';
import { useNavigate } from 'react-router-dom';
import { AppBar, Toolbar, Typography, Container, Button, Box } from '@mui/material';

const Home = () => {
  const navigate = useNavigate();

  const handleViewBovines = () => {
    navigate('/bovines'); // Ensure this route exists
  };

  const handleAddBovine = () => {
    navigate('/add-bovine');
  };

  return (
    <div>
      <AppBar position="static">
        <Toolbar>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            Cattle Farming Management
          </Typography>
        </Toolbar>
      </AppBar>
      <Container>
        <Box mt={4}>
          <Box mt={2}>
            <Button
              variant="contained"
              color="primary"
              onClick={handleViewBovines}
              sx={{ marginRight: 2 }}
            >
              View All Bovines
            </Button>
            <Button
              variant="contained"
              color="secondary"
              onClick={handleAddBovine}
            >
              Add Bovine
            </Button>
          </Box>
        </Box>
      </Container>
    </div>
  );
};

export default Home;
