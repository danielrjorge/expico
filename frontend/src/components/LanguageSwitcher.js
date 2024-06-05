import React from 'react';
import { FormControlLabel, Switch, Box, Typography } from '@mui/material';
import { useLanguage } from '../contexts/LanguageContext';

const LanguageSwitcher = () => {
    const { language, switchLanguage } = useLanguage();
  
    const handleToggle = () => {
      switchLanguage(language === 'en' ? 'pt' : 'en');
    };
  
    return (
      <Box sx={{ display: 'flex', alignItems: 'center' }}>
          <FormControlLabel
          control={
            <Switch
              checked={language === 'pt'}
              onChange={handleToggle}
              inputProps={{ 'aria-label': 'language switch' }}
            />
          }
        />
        <Typography variant="body1" sx={{ marginLeft: 1 }}>
          {language.toUpperCase()}
        </Typography>
      </Box>
    );
  };

  export default LanguageSwitcher;