import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Box, Container } from '@mui/material';
import Main from './pages/Main';
import AddBovine from './pages/AddBovine';
import { LanguageProvider } from './contexts/LanguageContext';
import CssBaseline from '@mui/material/CssBaseline';
import CustomAppBar from './components/CustomAppbar';

const App = () => (
  <LanguageProvider>
    <CssBaseline />
    <Router>
      <Container>
        <Box sx={{display:"flex", flexDirection: "column"}}>
        <CustomAppBar />
        <Routes>
          <Route path="/" element={<Main />} />
          <Route path="/add-bovine" element={<AddBovine />} />
          {/* Define other routes here */}
        </Routes>
        </Box>
      </Container>
    </Router>
  </LanguageProvider>
);

export default App;
