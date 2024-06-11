import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/api',
});

// Bovines
export const getAllBovines = () => api.get('/bovines/all');
export const getAllOwnedBovines = () => api.get('/bovines/owned');
export const getAllFarmBovines = () => api.get('/bovines/farm');
export const getBovineById = (bovineId) => api.get(`/bovines/id/${bovineId}`);
export const getBovineByCode = (bovineCode) => api.get(`/bovines/id/${bovineCode}`);
export const getBovinesInLand = (landCode) => api.get(`/bovines/land/${landCode}`);
export const addBovine = (bovine) => api.post('/bovines/', bovine);
export const updateBovine = (bovine) => api.put('/bovines/', bovine);
export const deleteBovine = (bovineCode) => api.delete(`/bovines/${bovineCode}`);

// Bovine Purchases
export const getAllBovinePurchases = () => api.post('/bovines/purchases');
export const addBovinePurchase = (purchaseRequest) => api.post('/bovines/purchases', purchaseRequest);

// Bovine Sales
export const getAllBovineSales = () => api.post('/bovines/sales');
export const addBovineSale = (saleRequest) => api.post('/bovines/sales', saleRequest);

// Bovine Butchers
export const getAllBovineButchers = () => api.post('/bovines/butchers');
export const addBovineButcher = (butcherRequest) => api.post('/bovines/butchers', butcherRequest);

// Owners
export const getAllOwners = () => api.get('/owners');
export const getOwnerById = (ownerId) => api.get(`/owners/id/${ownerId}`);
export const getOwnerByNif = (nif) => api.get(`/owners/nif/${nif}`);
export const addOwner = (newOwnerRequest) => api.post('/owners/', newOwnerRequest);
export const updateOwner = (updateOwnerRequest) => api.put('/owners', updateOwnerRequest);
export const deleteOwnerByNif = (nif) => api.delete(`/owners/${nif}`);

// Land
export const getAllLand = () => api.get('/land');
export const getLandByCode = (landCode) => api.get(`/land/${landCode}`);
export const addLand = (addLandRequest) => api.post('/land', addLandRequest);
export const updateLand = (updateLandRequest) => api.put('/land', updateLandRequest);
export const deleteLandByCode = (landCode) => api.delete(`/land/${landCode}`);

// Land Movement
export const getAllLandMovements = () => api.get('/movement');
export const addLandMovement = (landMovementRequest) => api.post(`/movement`, landMovementRequest);

export default api;
