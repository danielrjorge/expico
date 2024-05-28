// src/components/Bovines/BovineTable.js
import React, { useEffect, useState } from 'react';
import { getBovines } from '../../api/bovines';
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, CircularProgress } from '@mui/material';

const BovineTable = () => {
    const [bovines, setBovines] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchBovines = async () => {
            try {
                const data = await getBovines();
                setBovines(data);
            } catch (error) {
                console.error("Error fetching bovines", error);
            } finally {
                setLoading(false);
            }
        };

        fetchBovines();
    }, []);

    if (loading) {
        return <CircularProgress />;
    }

    return (
        <TableContainer component={Paper}>
            <Table>
                <TableHead>
                    <TableRow>
                        <TableCell>Codigo Bovino</TableCell>
                        <TableCell>Genero</TableCell>
                        {/* Add more columns as needed */}
                    </TableRow>
                </TableHead>
                <TableBody>
                    {bovines.map((bovine) => (
                        <TableRow key={bovine.bovineInternalId}>
                            <TableCell>{bovine.bovineCode}</TableCell>
                            <TableCell>{bovine.bovineGender}</TableCell>
                            {/* Add more columns as needed */}
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
};

export default BovineTable;
