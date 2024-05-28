// src/components/Sidebar.js
import React from 'react';
import { Drawer, List, ListItem, ListItemIcon, ListItemText, Toolbar } from '@mui/material';
import { Agriculture as AgricultureIcon } from '@mui/icons-material';
import '../App.css';

const Sidebar = ({ onShowBovines }) => {
    return (
        <Drawer
            className="drawer"
            variant="permanent"
            classes={{ paper: 'drawerPaper' }}
        >
            <Toolbar />
            <List>
                <ListItem button onClick={onShowBovines}>
                    <ListItemIcon>
                        <AgricultureIcon />
                    </ListItemIcon>
                    <ListItemText primary="Bovines" />
                </ListItem>
                {/* Add more items as needed */}
            </List>
        </Drawer>
    );
};

export default Sidebar;
