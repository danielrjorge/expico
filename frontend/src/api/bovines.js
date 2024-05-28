import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api/bovines';

export const getBovines = async () => {
    try {
        const response = await axios.get(`${API_BASE_URL}/all`);
        return response.data;
    } catch (error) {
        console.error("Error fetching bovines", error);
        throw error;
    }
};

// Add more functions for other CRUD operations