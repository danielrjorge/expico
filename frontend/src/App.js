import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Container } from '@mui/material';
import Main from './pages/Main';
import AddBovine from './pages/AddBovine';
import { LanguageProvider } from './contexts/LanguageContext';

const App = () => (
  <LanguageProvider>
    <Router>
      <Container>
        <Routes>
          <Route path="/" element={<Main />} />
          <Route path="/add-bovine" element={<AddBovine />} />
          {/* Define other routes here */}
        </Routes>
      </Container>
    </Router>
  </LanguageProvider>
);

export default App;
