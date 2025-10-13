import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api/players';
;

export const addPlayer = async (player) => {
  const response = await axios.post(`${API_BASE_URL}/addPlayer`, player);
  return response.data;
};

export const getAllPlayers = async () => {
  const response = await axios.get(`${API_BASE_URL}/allPlayers`);
  return response.data;
};

export const getPlayersByRole = async (role) => {
  const response = await axios.get(`${API_BASE_URL}/byRole?role=${role}`);
  return response.data;
};

export const getPlayersSortedByTeam = async () => {
  const response = await axios.get(`${API_BASE_URL}/sortedByTeam`);
  return response.data;
};

export const deletePlayer = async (id) => {
  const response = await axios.delete(`${API_BASE_URL}/${id}`);
  return response.data;
};