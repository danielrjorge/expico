import React from 'react';
import { Drawer, List, ListItemButton, ListItemText, ListItemIcon, Typography, Box, Divider } from '@mui/material';
import CowIcon from '@mui/icons-material/LocalFlorist';
import OwnerIcon from '@mui/icons-material/People';
import LandIcon from '@mui/icons-material/Terrain';
import MovementIcon from '@mui/icons-material/SwapHoriz';
import { useLanguage } from '../contexts/LanguageContext';

const Sidebar = ({ onSelect }) => {
  const { translations } = useLanguage();

  const menuItems = [
    { key: 'allCattle', icon: <CowIcon />, text: translations.allCattle },
    { key: 'farmCattle', icon: <CowIcon />, text: translations.farmCattle },
    { key: 'owners', icon: <OwnerIcon />, text: translations.owners },
    { key: 'land', icon: <LandIcon />, text: translations.land },
    { key: 'landMovements', icon: <MovementIcon />, text: translations.landMovements },
  ];

  const drawerWidth = 250;

  return (
    
    <Drawer
      variant="permanent"
      sx={{
          width: drawerWidth,
          flexShrink: 0,
          [`& .MuiDrawer-paper`]: { width: drawerWidth, boxSizing: 'border-box' },
        }}
    >
      <Box sx={{ display: 'flex', alignItems: 'center', justifyContent: 'center', padding: 2 }}>
        <Typography variant="h6">
          LOGO PLACEHOLDER
        </Typography>
      </Box>
      <Divider sx={{ backgroundColor: '#4B566B' }} />
      <List>
        {menuItems.map((item) => (
          <ListItemButton key={item.key} onClick={() => onSelect(item.key)}>
            <ListItemIcon >{item.icon}</ListItemIcon>
            <ListItemText primary={item.text} />
          </ListItemButton>
        ))}
      </List>
    </Drawer>
  );
};

export default Sidebar;
