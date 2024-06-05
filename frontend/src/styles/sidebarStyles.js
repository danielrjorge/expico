import { styled } from '@mui/material/styles';
import { createTheme } from '@mui/material/styles';

const theme = createTheme();

const drawerWidth = 240;

const useStyles = styled(() => ({
  sidebar: {
    width: drawerWidth,
    flexShrink: 0,
    backgroundColor: '#2E3B55', // Dark background for contrast
    color: '#FFFFFF',
  },
  drawerPaper: {
    width: drawerWidth,
    backgroundColor: '#2E3B55', // Dark background for contrast
    color: '#FFFFFF',
    borderRight: 'none',
    boxShadow: '2px 0 5px rgba(0, 0, 0, 0.1)',
  },
  listItem: {
    '&:hover': {
      backgroundColor: '#3E4B69',
    },
  },
  listItemText: {
    color: '#FFFFFF',
  },
  listItemIcon: {
    color: '#FFFFFF',
  },
  expicoText: {
    color: '#FFFFFF',
    fontWeight: 'bold',
  },
}));

export default useStyles;
