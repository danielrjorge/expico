import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Box, Container } from '@mui/material';
import Main from './pages/Main';
import AddBovine from './pages/AddBovine';
import { LanguageProvider } from './contexts/LanguageContext';
import CssBaseline from '@mui/material/CssBaseline';
import CustomAppBar from './components/CustomAppbar';
import GetAllBovines from './pages/GetAllBovines';
import UpdateBovine from './pages/UpdateBovine';
import AddOwner from './pages/AddOwner';
import GetAllOwners from './pages/GetAllOwners';

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
          <Route path="/update-bovine" element={<UpdateBovine />} />
          <Route path="/get-all-bovines" element={<GetAllBovines />} />
          <Route path="/add-owner" element={<AddOwner />} />
          <Route path="/get-all-owners" element={<GetAllOwners />} />
          {/* Define other routes here */}
        </Routes>
        </Box>
      </Container>
    </Router>
  </LanguageProvider>
);

export default App;
