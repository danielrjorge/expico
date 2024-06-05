import React from 'react';
import { Drawer, List, ListItemButton, ListItemText, ListItemIcon, Typography, Box, Divider } from '@mui/material';
import CowIcon from '@mui/icons-material/LocalFlorist';
import OwnerIcon from '@mui/icons-material/People';
import LandIcon from '@mui/icons-material/Terrain';
import MovementIcon from '@mui/icons-material/SwapHoriz';
import useStyles from '../styles/sidebarStyles';
import { useLanguage } from '../contexts/LanguageContext';

const Sidebar = ({ onSelect }) => {
  const classes = useStyles();
  const { translations } = useLanguage();

  const menuItems = [
    { key: 'allCattle', icon: <CowIcon />, text: translations.allCattle },
    { key: 'farmCattle', icon: <CowIcon />, text: translations.farmCattle },
    { key: 'owners', icon: <OwnerIcon />, text: translations.owners },
    { key: 'land', icon: <LandIcon />, text: translations.land },
    { key: 'landMovements', icon: <MovementIcon />, text: translations.landMovements },
  ];

  return (
    <Drawer
      variant="permanent"
      className={classes.drawer}
      classes={{
        paper: classes.drawerPaper,
      }}
    >
      <Box sx={{ display: 'flex', alignItems: 'center', justifyContent: 'center', padding: 2 }}>
        <Typography variant="h6" className={classes.expicoText}>
          Expico
        </Typography>
      </Box>
      <Divider sx={{ backgroundColor: '#4B566B' }} />
      <List>
        {menuItems.map((item) => (
          <ListItemButton key={item.key} onClick={() => onSelect(item.key)} className={classes.listItem}>
            <ListItemIcon className={classes.listItemIcon}>{item.icon}</ListItemIcon>
            <ListItemText primary={item.text} className={classes.listItemText} />
          </ListItemButton>
        ))}
      </List>
    </Drawer>
  );
};

export default Sidebar;
