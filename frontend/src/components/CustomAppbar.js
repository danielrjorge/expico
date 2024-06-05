import React from 'react';
import { AppBar, Toolbar, Typography, Box } from '@mui/material';
import { useLanguage } from '../contexts/LanguageContext';
import LanguageSwitcher from './LanguageSwitcher';

const CustomAppBar = () => {

    const { language } = useLanguage();
  
    return (
        <AppBar position="static" sx={{ backgroundColor: language === 'en' ? 'default' : '#4caf50' }}>
        <Toolbar>
          <Typography variant="h6" sx={{ flexGrow: 1 }}>
            Expico
          </Typography>
          <Box sx={{ display: 'flex', alignItems: 'center' }}>
            <LanguageSwitcher />
          </Box>
        </Toolbar>
      </AppBar>
    );
  };

  export default CustomAppBar;