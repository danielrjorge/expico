import './App.css';
import React, { useState } from 'react';
import { CssBaseline, Box, Toolbar, AppBar, Typography } from '@mui/material';
import Sidebar from './components/Sidebar';
import BovineTable from './components/Bovines/BovineTable';
import './App.css';

function App() {
    const [showBovines, setShowBovines] = useState(false);

    const handleShowBovines = () => {
        setShowBovines(true);
    };

    return (
        <Box sx={{ display: 'flex' }}>
            <CssBaseline />
            <AppBar position="fixed" className="appBar">
                <Toolbar>
                    <Typography variant="h6" noWrap>
                        Cattle Management
                    </Typography>
                </Toolbar>
            </AppBar>
            <Sidebar onShowBovines={handleShowBovines} />
            <Box
                component="main"
                className="content"
            >
                {showBovines && <BovineTable />}
            </Box>
        </Box>
    );
};

export default App;
